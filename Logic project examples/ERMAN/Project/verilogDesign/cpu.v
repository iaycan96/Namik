`timescale 1 ns/1 ns
module cpu ();

  reg [19:0] InstructionMemory[0:63],ram[0:64],nextInst,mux1o=0,raminit=0;
  reg clk=0,alu_mux_en=1,mux2sel=0,jen=0,jsel=0;
  wire [11:0] pc_to_rom,pc_out,jump_imm;
  reg [3:0] opc;
  wire [3:0] destreg,regsrc1,regsrc2;
  wire [7:0] immtoregfile;
  reg [15:0] inst_left;
  reg [11:0] pc_old=0,pc_add=1,rom_ct=0,toplam=0;
  wire [9:0] mem_addr;
  wire [19:0] alu_result,reg1_o,reg2_o;

  wire mem_st_enable,mem_ld_enable,reg_ld_enable,jump_imm_sel,jmp_en;
  initial begin
  toplam = 0;
  pc_add=1;
  while(raminit<64) begin
    ram[raminit]=0;
    raminit=raminit+1;
  end

  $readmemh("input.hex",InstructionMemory);
  end
  always begin
    clk <= ~clk;
    #50;
  end

  always @(posedge clk) begin
    while((pc_out-pc_old)>0) begin
      rom_ct=rom_ct+1;
      pc_old=pc_old+1;
    end
    while((pc_out-pc_old)<0) begin
      rom_ct = rom_ct-1;
      pc_old=pc_old-1;
    end
    nextInst=InstructionMemory[rom_ct];
    opc = nextInst[19:16];
    inst_left = nextInst[15:0];
    if(mem_ld_enable==1) begin
      mux1o=ram[mem_addr];
    end else begin
      mux1o=alu_result;
    end
    mux2sel=jmp_en|jump_imm_sel;
    if(mux2sel==0) begin
      pc_add=1;
    end else if(mux2sel==1) begin
      pc_add=jump_imm;

    end
  end

  pc prog_c(pc_add,clk,pc_out);
  control_unit cu(opc,inst_left,mem_st_enable,reg_ld_enable,mem_ld_enable,mem_addr,destreg,regsrc1,regsrc2,immtoregfile,jump_imm,jump_imm_sel);
  reg_file reg_file(mux1o,destreg,regsrc1,regsrc2,alu_mux_en,alu_mux_en,clk,reg1_o,reg2_o);
  alu alu(reg1_o,reg2_o,immtoregfile,opc,alu_result,jmp_en);

endmodule
