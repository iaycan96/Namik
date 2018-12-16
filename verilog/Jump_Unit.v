`timescale 1 ns/1 ns

module jump_unit(
  input [3:0] opcode
  input ZF, CF,
  output reg jumpOK
);

always @(*) begin
  case (opcode)
    4'0111 : jumpOK = 1;
    4'1000 : jumpOK = ZF & ~CF;
    4'1001 : jumpOK = ~ZF & ~CF;
    4'1010 : jumpOK = ~ZF & CF;
    4'1011 : jumpOK = ZF & CF;
    4'1100 : jumpOK = ~CF;
    default: jumpOK = 0;
  endcase
end

endmodule // jump_unit