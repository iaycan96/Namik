module D_FlipFlop(D,clk,Q);
input D,clk;
output Q;
reg Q;
always @(D or clk)
if (clk)
	Q=D;
endmodule

