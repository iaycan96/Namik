module control_unit #(parameter N=3,parameter M=15,parameter J=9,parameter K=7,parameter L=11)(opc,inst_left,mem_store_enable,reg_load_enable,mem_load_enable,mem_address,reg_no_dest,reg_no_src1,reg_no_src2,imm,jump_imm,jump_imm_sel);
  input [N:0] opc;
  input [M:0] inst_left;
  output mem_store_enable,mem_load_enable,reg_load_enable,jump_imm_sel;
  output [J:0] mem_address;
  output [N:0] reg_no_dest,reg_no_src1,reg_no_src2;
  output [K:0] imm;
  output [L:0] jump_imm;
  reg mem_store_enable,mem_load_enable,reg_load_enable,jump_imm_sel;
  reg [J:0] mem_address;
  reg [N:0] reg_no_dest,reg_no_src1,reg_no_src2;
  reg [K:0] imm;
  reg [L:0] jump_imm;

  always @* begin
    mem_store_enable=0;
    mem_load_enable=0;
    reg_load_enable=0;
    jump_imm_sel=0;

    case(opc)
    4'b0000:
      begin
        reg_no_dest<=inst_left[15:12];
        mem_address<=inst_left[9:0];
        reg_load_enable<=1'b1;
        mem_load_enable<=1'b1;
      end

    4'b0001,4'b0010,4'b0011,4'b0100:
      begin
        reg_no_dest<=inst_left[15:12];
        reg_no_src1<=inst_left[11:8];
        reg_no_src2<=inst_left[7:4];
        reg_load_enable<=1'b1;
      end

    4'b0101,4'b0110,4'b0111,4'b1000:
      begin
        reg_no_dest<=inst_left[15:12];
        reg_no_src1<=inst_left[11:8];
        imm<=inst_left[7:0];
        reg_load_enable<=1'b1;
      end

    4'b1001,4'b1010,4'b1011,4'b1100,4'b1110:
      begin
        reg_no_src1<=inst_left[15:12];
        reg_no_src2<=inst_left[11:8];
        jump_imm<={{4{inst_left[4]}},inst_left[7:0]};
      end

    4'b1101:
      begin
        jump_imm<=inst_left[11:0];
        jump_imm_sel<=1'b1;
      end

    4'b1111:
    begin
      reg_no_src1<=inst_left[15:12];
      mem_address<=inst_left[9:0];
      mem_store_enable<=1'b1;
    end
     endcase
  end
endmodule
