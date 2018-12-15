module half_comparator(
	input a,
	input b,
	output gt, eq, lt
);

assign gt = a & ~b;
assign eq = ~(a ^ b);
assign lt = ~a & b;

endmodule