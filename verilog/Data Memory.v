`timescale 1 ns/1 ns

module data_memory #(parameter bit_size = 15)(
    
    input dataMemRead, dataMemWrite,
    input [bit_size:0] value,
    input [11:0] address,
    output reg [bit_size:0] out
);

reg [15:0] data_memory[0:12'hFFF];
integer i;

initial begin
  for (i = 0; i<12'hFFF+1; i=i+1) begin
    data_memory[i] <= 16'h0000;
  end
end

always @(*) begin
    if(dataMemWrite) begin
      data_memory[address] = value;
    end else if(dataMemRead) begin
      out = data_memory[address];
    end
end

endmodule // 16bit_adder