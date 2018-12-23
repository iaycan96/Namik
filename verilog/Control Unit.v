`timescale 1 ns/1 ns

module control_unit(
  input [3:0] opcode,
  output reg dataMemRead, dataMemWrite, regWrite, immediate, ALUand, ALUadd, comparator, PCselect //signals
);

initial begin //assign 0 at starting point
  dataMemRead <= 0;
  dataMemWrite <= 0;
  regWrite <= 0;
  immediate <= 0;
  ALUand <= 0;
  ALUadd <= 0;
  comparator <= 0;
  PCselect <= 0;
end

always @(*) begin
  case (opcode)
    4'b0000 : begin dataMemRead = 0; dataMemWrite = 0; regWrite = 1; immediate = 0; ALUand = 0; ALUadd = 1; comparator = 0; PCselect = 0; end
    4'b0001 : begin dataMemRead = 0; dataMemWrite = 0; regWrite = 1; immediate = 1; ALUand = 0; ALUadd = 1; comparator = 0; PCselect = 0; end
    4'b0010 : begin dataMemRead = 0; dataMemWrite = 0; regWrite = 1; immediate = 0; ALUand = 1; ALUadd = 0; comparator = 0; PCselect = 0; end
    4'b0011 : begin dataMemRead = 0; dataMemWrite = 0; regWrite = 1; immediate = 1; ALUand = 1; ALUadd = 0; comparator = 0; PCselect = 0; end
    4'b0100 : begin dataMemRead = 1; dataMemWrite = 0; regWrite = 1; immediate = 0; ALUand = 0; ALUadd = 0; comparator = 0; PCselect = 0; end
    4'b0101 : begin dataMemRead = 0; dataMemWrite = 1; regWrite = 0; immediate = 0; ALUand = 0; ALUadd = 0; comparator = 0; PCselect = 0; end
    4'b0110 : begin dataMemRead = 0; dataMemWrite = 0; regWrite = 0; immediate = 0; ALUand = 0; ALUadd = 0; comparator = 1; PCselect = 0; end
    default : begin dataMemRead = 0; dataMemWrite = 0; regWrite = 0; immediate = 0; ALUand = 0; ALUadd = 0; comparator = 0; PCselect = 1; end
  endcase
end

endmodule // control_unit
