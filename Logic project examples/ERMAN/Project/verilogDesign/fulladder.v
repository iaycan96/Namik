`timescale 1 ns/1 ns
module FullAdder #(parameter N=19)(i_0,i_1,o_0,c_out);
  input [N:0] i_0,i_1;
  output [N:0] o_0;
  reg [N:0] o_0;
  output c_out;
  reg c_out;
  
  reg [N+1:0] i_0e,i_1e,o_0e;

  always @(i_0,i_1) begin
    o_0 <= i_0 + i_1;
    i_0e = {1'b0,i_0}; i_1e = {1'b0,i_1};
    o_0e = i_0e + i_1e;
    o_0 <= o_0e [N:0];
    c_out <= o_0e [N+1];
  end
endmodule
