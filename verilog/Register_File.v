`timescale 1 ns/1 ns

module register_file(
  input [3:0] read_register1, read_register2, write_register,
  input [15:0] write_value
  input regWrite_signal, clock
  output out1, out2
);

reg [15:0] register [15:0];
integer i;

initial begin
  for(i=0;i<16;i=i+1)
    register[i] <= 16'd0;
end

always @(posedge clock) begin
  if(regWrite_signal) begin
    register[write_register] = write_value;
  end
end

assign out1 = register[read_register1];
assign out2 = register[read_register2];

endmodule // register_file