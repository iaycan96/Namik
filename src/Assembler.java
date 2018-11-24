import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Math;;

public class Assembler {
    static Scanner input = null;

    public static void main(String[]args)
    {
        ArrayList<String> instructions=new ArrayList<String>();
       // String instructions[]= new String[100];
        try
        {
            input = new Scanner(new File("input.txt"));
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.err.println("Error, file could not found.");
            System.exit(1);
        }
        try
        {
            while (input.hasNext()) ///Reading from file
            {
                for (int i = 0; input.hasNext(); i++) {
                    instructions.add(input.nextLine());
                   // instructions[i] = input.nextLine();
                 //   System.out.println(instructions.get(i)+" is instruction");
                }
            }
            function(instructions);

            System.out.println("uzunluğu :"+instructions.size());


        }

        catch (NoSuchElementException elementException)
        {
            System.err.println("File improperly formed.");
            input.close();
            System.exit(1);
        }
        catch (IllegalStateException stateException)
        {
            System.err.println("Error reading from file.");
            System.exit(1);
        }



    }



    public static String getopcode(String ins){
        String buffer="";

        if(ins.equalsIgnoreCase("ADD")){
            buffer="0000";

        }
        if(ins.equalsIgnoreCase("ADDI")){
            buffer="0001";
        }
        if(ins.equalsIgnoreCase("AND")){
            buffer="0010";
        }
        if(ins.equalsIgnoreCase("ANDI")){
            buffer="0011";
        }
        if(ins.equalsIgnoreCase("LD")){
            buffer="0100";
        }
        if(ins.equalsIgnoreCase("ST")){
            buffer="0101";
        }
        if(ins.equalsIgnoreCase("CMP")){
            buffer="0110";
        }
        if(ins.equalsIgnoreCase("JUMP")){
            buffer="0111";
        }
        if(ins.equalsIgnoreCase("JE")){
            buffer="1000";
        }
        if(ins.equalsIgnoreCase("JA")){
            buffer="1001";
        }
        if(ins.equalsIgnoreCase("JB")){
            buffer="1010";
        }
        if(ins.equalsIgnoreCase("JBE")){
            buffer="1011";
        }
        if(ins.equalsIgnoreCase("JAE")){
            buffer="1100";
        }
        return buffer;

    }

    public static String addrToBinary(String s){
        String temp=s;
        int val =Integer.parseInt(temp);
        temp=Integer.toBinaryString(val);
        while ((temp.length()<7)){
            temp="0"+temp;
        }
        return temp;
    }

    public static String immToBinary(String s){
        String temp=s;
        int val= Integer.parseInt(temp);
        temp=Integer.toBinaryString(val);
        if(val>=0){
            while(temp.length()<4){
                temp="0"+temp;
            }
        }
        else
            temp=temp.substring(temp.length()-4,temp.length());
        return temp;
    }
    public static String conditionalJump(String opcode,String s){
        String temp=s;
        String addr;
        int index=temp.indexOf(' ');
        addr=temp.substring(index+2,temp.length());
        addr=addrToBinary(addr);
        return opcode+"00000"+addr;

    }

    public static String toOffset(String s){
        String temp= s;
        int val=Integer.parseInt(temp);
        temp=Integer.toBinaryString(val);
        if(val>0){
            while (temp.length()<8){
                temp="0"+temp;
            }
        }
        else{
            temp=temp.substring(temp.length()-8,temp.length());
        }
        return temp;
    }


    public static String regToBinary(String s){
        String temp=s.substring(1,s.length());
        int val=Integer.parseInt(temp);
        temp=Integer.toBinaryString(val);
        while(temp.length()<4){
            temp="0"+temp;
        }

      //  System.out.println(s+"-"+temp);
        return temp;
    }

    public static void function(ArrayList<String> instructions){
        int index1,index2;
        index1=0;
        index2=0;

        String opcode,r1,r2,r3,addr,imm,op1,op2,offset,zf,cf;
        String temp;

        for(int i=0;i<instructions.size();i++){
            temp=instructions.get(i);
            index1 =temp.indexOf(' ');
            opcode=temp.substring(0, index1);
            opcode=getopcode(opcode);
           // System.out.println(i+":"+opcode);

            if(opcode=="0000"){
                index2=temp.indexOf(',');
                r1= temp.substring(index1+1, index2); // Extract first word.
                temp=temp.substring(index2+1,temp.length());
                index2=temp.indexOf(',');
                r2=temp.substring(0,index2);
                r3=temp.substring(index2+1,temp.length());
                r1=regToBinary(r1);
                r2=regToBinary(r2);
                r3=regToBinary(r3);
                temp=opcode+r1+r2+r3;
                System.out.println(instructions.get(i)+" :"+temp);
            }
            if(opcode=="0001"){
                index2=temp.indexOf(',');
                r1= temp.substring(index1+1, index2);
                temp=temp.substring(index2+1,temp.length());
                index2=temp.indexOf(',');
                r2=temp.substring(0,index2);
                imm=temp.substring(index2+1,temp.length());
                r1=regToBinary(r1);
                r2=regToBinary(r2);
                imm=immToBinary(imm);
                temp=opcode+r1+r2+imm;
                System.out.println(instructions.get(i)+" :"+temp);
            }
            if(opcode=="0010"){
                index2=temp.indexOf(',');
                r1= temp.substring(index1+1, index2); // Extract first word.
                temp=temp.substring(index2+1,temp.length());
                index2=temp.indexOf(',');
                r2=temp.substring(0,index2);
                r3=temp.substring(index2+1,temp.length());
                r1=regToBinary(r1);
                r2=regToBinary(r2);
                r3=regToBinary(r3);
                temp=opcode+r1+r2+r3;
                System.out.println(instructions.get(i)+":"+temp);
            }
            if(opcode=="0011"){
                index2=temp.indexOf(',');
                r1= temp.substring(index1+1, index2);
                temp=temp.substring(index2+1,temp.length());
                index2=temp.indexOf(',');
                r2=temp.substring(0,index2);
                imm=temp.substring(index2+1,temp.length());
                r1=regToBinary(r1);
                r2=regToBinary(r2);
                imm=immToBinary(imm);
                temp=opcode+r1+r2+imm;
                System.out.println(instructions.get(i)+":"+temp);
            }
            if(opcode=="0100"){
                index2=temp.indexOf(',');
                r1= temp.substring(index1+1, index2);
                addr=temp.substring(index2+1,temp.length());
                r1=regToBinary(r1);
                addr=addrToBinary(addr);
                temp=opcode+r1+"0"+addr;
                System.out.println(instructions.get(i)+":"+temp);
            }
            if(opcode=="0101"){
                index2=temp.indexOf(',');
                r1= temp.substring(index1+1, index2);
                addr=temp.substring(index2+1,temp.length());
                r1=regToBinary(r1);
                addr=addrToBinary(addr);
                temp=opcode+r1+"0"+addr;
                System.out.println(instructions.get(i)+":"+temp+"");
            }
            if(opcode=="0110"){ //CMP
                index2=temp.indexOf(',');
                op1= temp.substring(index1+1, index2);
                op2=temp.substring(index2+1,temp.length());
                op1=regToBinary(op1);
                op2=regToBinary(op2);
                temp=opcode+"0000"+op1+op2;
                System.out.println(instructions.get(i)+":"+temp);


            }
            if(opcode=="0111"){//JUMP
                offset=temp.substring(index1+1,temp.length());
                offset=toOffset(offset);
                temp=opcode+"0000"+offset;
                System.out.println(instructions.get(i)+":"+temp);

            }
            if(opcode=="1000"||opcode=="1001"||opcode=="1010"||opcode=="1011"||opcode=="1100"){
                temp=   conditionalJump(opcode,temp);
                System.out.println(instructions.get(i)+":"+temp);


            }

        }
    }
}








