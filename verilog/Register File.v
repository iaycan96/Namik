`timescale 1 ns/1 ns

module register_file(
  input [3:0] read_register1, read_register2, write_register,
  input [15:0] write_value,
  input regWrite_signal,
  output reg [15:0] out1, out2
);

reg [15:0] register [0:15];
integer i;

initial begin
  for(i=0;i<16;i=i+1)
    register[i] <= 16'h0000;
end

always @(*) begin
  if(regWrite_signal) begin
    register[write_register] = write_value;
  end
  out1 = register[read_register1];
  out2 = register[read_register2];
end

endmodule // register_file
