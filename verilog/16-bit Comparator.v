`timescale 1 ns/1 ns

module comparator_16bit #(parameter bit_size = 15)(
    
    input [bit_size:0] a, b,
    output reg gt_out, eq_out, lt_out
);

always @(*) begin
    if (a>b) begin
        gt_out = 1;
        eq_out = 0;
        lt_out = 0;
    end 
    else if (a==b) begin
        gt_out = 0;
        eq_out = 1;
        lt_out = 0;

    end
    else if (a<b) begin
        gt_out = 0
        eq_out = 0
        lt_out = 1
    end
end

endmodule // comparator_16bit