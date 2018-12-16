`timescale 1 ns/1 ns

module cpu ();

reg [11:0] PC;
reg clock, ZF, CF;
reg [15:0] instruction_memory[11:0], data_memory[11:0];

initial begin
  clock = 0;
end

always @(*) begin
  clock = ~clock;
  #50;
end

adder_12bit ins_adder_12bit();
alu ins_alu();

endmodule // cpu