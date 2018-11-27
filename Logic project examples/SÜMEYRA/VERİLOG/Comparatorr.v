module Comparator #(parameter N=4)(A, B, Gt, Eq, Lt);
  
  input [N:0] A;
  input signed [N:0] B;
  output Gt, Eq, Lt;
  reg Gt, Eq, Lt;
  
  always @(A, B) begin
    if($signed({1'b0,A}) < B) begin
      Gt<=0; Eq <=0; Lt<=1;
    end
    else if($signed({1'b0,A}) > B) begin
      Gt<=1; Eq<=0; Lt<=0;
    end
    else begin
      Gt<=0;  Eq<=1;  Lt<=0;
    end
  end
endmodule
