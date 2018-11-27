module mux16 #(parameter N=19, parameter M=15,parameter J=3)(opc,r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,out);
    input [N:0] r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15;
    input [J:0] opc;
    output [N:0] out;
    reg [N:0] out;
    always @* begin
    if(opc==4'b0000)begin
      out<=r0;
    end else if(opc==4'b0001) begin
    out<=r1;
   end else if(opc==4'b0010) begin
      out<=r2;
    end else if(opc==4'b0011) begin
      out<=r3;
    end else if(opc==4'b0100) begin
      out<=r4;
    end else if(opc==4'b0101) begin
      out<=r5;
    end else if(opc==4'b0110) begin
      out<=r6;
    end else if(opc==4'b0111) begin
      out<=r7;
    end else if(opc==4'b1000) begin
      out<=r8;
    end else if(opc==4'b1001) begin
      out<=r9;
    end else if(opc==4'b1010) begin
      out<=r10;
    end else if(opc==4'b1011) begin
      out<=r11;
    end else if(opc==4'b1100) begin
      out<=r12;
    end else if(opc==4'b1101) begin
      out<=r13;
    end else if(opc==4'b1110) begin
      out<=r14;
    end else if(opc==4'b1111) begin
      out<=r15;
    end
end
endmodule
