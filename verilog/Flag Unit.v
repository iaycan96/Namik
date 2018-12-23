`timescale 1 ns/1 ns

module flag_unit #(parameter N = 15)(
  input [15:0] a, b,
  output reg ZF, CF
);

wire gt, eq, lt;

always @(*) begin
  if(gt == 1) begin
    ZF = 0; CF = 0;
  end else if(eq == 1) begin
    ZF = 1; CF = 0;
  end else if(lt == 1) begin
    ZF = 0; CF = 1;
  end
end

comparator_16bit ins_comparator_16bit(.a(a), .b(b), .gt_out(gt), .eq_out(eq), .lt_out(lt));
endmodule // flag_unit