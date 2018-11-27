module alu(
           input [19:0] A,B,  // ALU 20-bit Inputs                 
           input [1:0] ALU_Sel,// ALU Selection
           output [19:0] ALU_Out // ALU 20-bit Output
           
    );
    reg [19:0] ALU_Result;
    assign ALU_Out = ALU_Result; // ALU out
     always @(*)
    begin
        case(ALU_Sel)
        2'b00: // And
           ALU_Result = A & B; 
        2'b01: // Xor
          ALU_Result = A ^ B;
        2'b10: // Or
            ALU_Result = A | B;
        2'b11: //  Add
           ALU_Result = A+B;
		endcase
	end
endmodule