`timescale 1 ns/1 ns

module adder_12bit #(parameter bit_size = 11)(
    
    input [bit_size:0] a, b,
    output reg [bit_size:0] out
);

always @(*) begin
    out = a + b;
end

endmodule // adder_12bit