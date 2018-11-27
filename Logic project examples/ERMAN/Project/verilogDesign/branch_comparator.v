`timescale 1 ns/1 ns
module branch_comparator #(parameter N=19,parameter M=3)(sub_result,opc,jump_select);
  input [N:0] sub_result;
  input [M:0] opc;
  output jump_select;
  reg [N:0] ct=N;
  reg jump_select;
  reg neg,eq,pos,a=0,b=0,enable=1;
  always @(sub_result,opc) begin
      neg = sub_result[N] & opc[2];
      while(ct>0) begin
        a=a|sub_result[ct];
        if(ct<N)begin
          b=b|sub_result[ct];
        end
        ct=ct-1;
      end
  eq=~a&opc[1];
  pos=opc[0]&(b&~sub_result[N]);
  jump_select <= pos|neg|eq;
  end
endmodule
