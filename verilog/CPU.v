`timescale 1 ns/1 ns

module cpu ();

reg [11:0] PC, dataMemAdress;
reg clock, ZF, CF, PC_flag;
reg [15:0] instruction_memory[0:12'hFFF], data_memory[0:12'hFFF];
reg [15:0] instruction, write_value, alu_in;
reg [3:0] opcode, read_register1, read_register2, write_register;
wire dataMemRead, dataMemWrite, regWrite, immediate, ALUand, ALUadd, comparator, PCselect; //signals
wire zf, cf, jumpOK;
wire [15:0] reg_out1, reg_out2, alu_out;
integer i;

initial begin
  clock <= 0;
  PC <= 12'h000;
  ZF <= 0;
  CF <= 0;
  PC_flag <= 0;

  for (i = 0; i<12'hFFF+1; i=i+1) begin
    data_memory[i] <= 16'h0000;
  end

  $readmemh("v_output.hex", instruction_memory); //read from file
end

always begin  //generate clock cycle
  #50 clock = ~clock;
end

always @(*) begin

  read_register1 = instruction[7:4];
  write_register = instruction[11:8];
  dataMemAdress = {4'h0, instruction[7:0]};

  if(dataMemWrite) begin
    read_register2 = instruction[11:8];
    data_memory[dataMemAdress] = reg_out2;
  end else begin
    read_register2 = instruction[3:0];
  end

  if(immediate) begin
    alu_in = {4'h0, read_register2};
  end else begin
    alu_in = reg_out2;
  end

  if((ALUadd | ALUand) & ~dataMemWrite) begin
    write_value = alu_out;
  end else begin
    write_value = data_memory[dataMemAdress];
  end

  if(comparator) begin
    ZF = zf;
    CF = cf;
  end

  if(PCselect & jumpOK) begin
    PC_flag = 1;
  end else begin
    PC_flag = 0;
  end
end

always @(posedge clock) begin

  if(PC_flag) begin
    PC = PC + instruction[11:0];
  end else begin
    PC = PC + 1;
  end

  instruction = instruction_memory[PC];
  opcode = instruction[15:12];
  
end

jump_unit ins_jump_unit(.opcode(opcode), .ZF(ZF), .CF(CF), .jumpOK(jumpOK));
alu ins_alu(.a(reg_out1), .b(alu_in), .ALUand(ALUand), .ALUadd(ALUadd), .out(alu_out));
flag_unit ins_flag_unit(.a(reg_out1), .b(reg_out2), .ZF(zf), .CF(cf));
register_file ins_register_file(.read_register1(read_register1), .read_register2(read_register2), .write_register(write_register), .write_value(write_value), .regWrite_signal(regWrite), .out1(reg_out1), .out2(reg_out2));
control_unit ins_control_unit(.opcode(opcode), .dataMemRead(dataMemRead), .dataMemWrite(dataMemWrite), .regWrite(regWrite), .immediate(immediate), .ALUand(ALUand), .ALUadd(ALUadd), .comparator(comparator), .PCselect(PCselect));

endmodule // cpu
