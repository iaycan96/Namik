`timescale 1 ns/1 ns

module alu #(parameter bit_size = 15)(
    input [bit_size:0] a, b,
    input ALUand, ALUadd, //signals
    output reg [bit_size:0] out
);

wire [bit_size:0] out_adder_16bit;

always @(*) begin
    if (ALUand == 1 & ALUadd == 0) begin
      out = a & b;
    end else if (ALUand == 0 & ALUadd == 1) begin
      out = out_adder_16bit;
    end
end

adder_16bit ins_adder_16bit(.a(a), .b(b), .out(out_adder_16bit));

endmodule // alu
