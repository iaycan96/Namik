`timescale 1 ns/1 ns

module control_unit(
  input [3:0] opcode,
  output reg dataMemRead, dataMemWrite, regWrite, immediate, ALUand, ALUadd, comparator, PCselect
);

initial begin
  dataMemRead <= 0;
  dataMemRead <= 0;
  regWrite <= 0;
  immediate <= 0;
  ALUand <= 0;
  ALUadd <= 0;
  comparator <= 0;
  PCselect <= 0;
end

always @(*) begin
  case (opcode)
    4'b0000 : begin regWrite = 1; ALUadd = 1; end
    4'b0001 : begin regWrite = 1; immediate = 1; ALUadd = 1; end
    4'b0010 : begin regWrite = 1; ALUand = 1; end
    4'b0011 : begin regWrite = 1; immediate = 1; ALUand = 1; end 
    4'b0100 : begin dataMemRead = 1; regWrite = 1; end 
    4'b0101 : begin dataMemWrite = 1; end
    default : begin PCselect = 1; end
  endcase
end

endmodule // control_unit