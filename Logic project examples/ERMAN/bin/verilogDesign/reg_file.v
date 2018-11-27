`timescale 1 ns/1 ns
module reg_file #(parameter N=19, parameter M=3)(in,dest,s0,s1,s0_mux_enable,s1_mux_enable,clk,o1,o2);

  input [N:0] in;
  input [M:0] dest, s0, s1;
  input s0_mux_enable, s1_mux_enable, clk;
  output [N:0] o1, o2;
  wire [N:0] mux16_o0,mux16_o1;
  reg [N:0] r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15;
  reg [N:0] o1,o2,asd;
  always @(posedge clk)begin
      if(s0_mux_enable == 1) begin
        o1<=mux16_o0;
      end
      if(s1_mux_enable == 1) begin
        o2<=mux16_o1;
      end
  end
  always @(negedge clk) begin
    if(s1_mux_enable == 1) begin
      case(dest)
        4'b0000:r0=in;
        4'b0001:r1=in;
        4'b0010:r2=in;
        4'b0011:r3=in;
        4'b0100:r4=in;
        4'b0101:r5=in;
        4'b0110:r6=in;
        4'b1000:r8=in;
        4'b0111:r7=in;
        4'b1001:r9=in;
        4'b1010:r10=in;
        4'b1011:r11=in;
        4'b1100:r12=in;
        4'b1101:r13=in;
        4'b1110:r14=in;
        4'b1111:r15=in;
      endcase
    end
  end
  mux16 mux16_0(s0,r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,mux16_o0);
  mux16 mux16_1(s1,r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,mux16_o1);


endmodule
