`timescale 1 ns/1 ns

module adder_16bit #(parameter bit_size = 15)(
    
    input [bit_size:0] a, b,
    output reg [bit_size:0] out
);

always @(*) begin
    out = a + b;
end

endmodule // 16bit_adder
