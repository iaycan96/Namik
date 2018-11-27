`timescale 1 ns/1 ns
module alu #(parameter N=19,parameter M=7,parameter J=3)(i0,i1,imm,opc,result,jump_enable);
  input [N:0] i0,i1;
  input [M:0] imm;
  input [J:0] opc;
  output [N:0] result;
  reg [N:0] result;
  output jump_enable;
  reg jump_enable;
  wire jump_en;

  //wire [N:0] i0,i1;
  wire [N:0] faneg_o;
  wire faneg_c_out;
  wire [N:0]imm_ext = {12'b000000000000,imm};
  reg d1tom1=0;
  reg d2tom2=0;
  wire je;
  wire [N:0] fa_o;
  reg [N:0] and_o, xor_o, or_o;
  wire fa_c_out;
  reg [N:0]m2out;
  reg [N:0]m1out;



  always @* begin

    if((opc>4'b1000)&(opc<4'b1111))begin
      d2tom2=1;
    end else if((opc>4'b0100)&(opc<4'b1001)) begin
      d1tom1=1;
    end

    if(d1tom1==0)begin
      m1out = i1;
    end else begin
      m1out = imm_ext;
    end

    and_o=m1out&i0;
    xor_o=m1out^i0;
    or_o=m1out|i0;

    if(d2tom2==0)begin
      m2out <= m1out;
    end else begin
      m2out <= faneg_o;
    end

    if(opc == 4'b0100 | opc == 4'b0101)begin
      result <= fa_o;
    end else if(opc == 4'b0011 | opc == 4'b1000)begin
      result <= and_o;
    end else if(opc == 4'b0001 | opc == 4'b0111)begin
      result <= xor_o;
    end else if(opc == 4'b0010 | opc == 4'b0110)begin
      result <= or_o;
    end else begin
      result <= 0;
    end
    if((opc!=4'b1001)&(opc!=4'b1010)&(opc!=4'b1011)&(opc!=4'b1100)&(opc!=4'b1110)) begin
      jump_enable<=1'b0;
    end else if(opc[3]==1) begin
      jump_enable<=jump_en;
    end

  end

  FullAdder faneg(00001,~i1,faneg_o,faneg_c_out);
  FullAdder fa(i0,m2out,fa_o,fa_c_out);
  branch_comparator bc(fa_o,opc,jump_en);



endmodule
