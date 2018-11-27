`timescale 1 ns/1 ns
module pc#(parameter N=11)(add_value,clk,pc_value);
  input [N:0] add_value;
  input clk;
  output [N:0] pc_value;
  wire [N:0] add_value;
  reg [N:0] pc_reg = 12'b0000_0000_0000;
  reg [N:0] reg_new = 12'b0000_0000_0000;
  reg [N:0] pc_value = 0;

  always @(posedge clk)begin

    reg_new = add_value + pc_reg;
    pc_value <= pc_reg;

  end

  always @(negedge clk)begin

    pc_reg = reg_new;

  end
endmodule
