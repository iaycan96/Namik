
module Register(input Clk, input ShiftIn,
  input [19:0] ParallelIn, input load, input ShiftEn, 
  output ShiftOut, output [19:0] RegContent);
  reg [19:0] shift_reg;
always @(posedge Clk)
 if(load)
  shift_reg <= ParallelIn;
 else if (ShiftEn)
  shift_reg <= {shift_reg[19:0], ShiftIn};
  assign ShiftOut = shift_reg[19];
  assign RegContent = shift_reg;
endmodule