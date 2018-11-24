import java.io.*;
import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.lang.Math;;

public class Assembler {
    private static Writer writer,writer2;
    private static Scanner input = null;
    private static boolean first = true;

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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String binaryToHex(String s){
        String temp;
        String retValue;
        retValue="";
        int i=0;
        int j=0;
        while(j<=3) {
            temp=s.substring(i,i+4);


            if(temp.equals("0000")){
                temp="0";

            }
            if (temp.equals("0001")) {
                temp="1";
            }
            if (temp.equals("0010")) {
                temp="2";
            }
            if (temp.equals("0011")) {
                temp="3";
            }
            if (temp.equals("0100")) {
                temp="4";
            }
            if (temp.equals("0101")) {
                temp="5";
            }
            if (temp.equals("0110")) {
                temp="6";
            }
            if (temp.equals("0111")) {
                temp="7";
            }
            if (temp.equals("1000")) {
                temp="8";
            }
            if (temp.equals("1001")) {
                temp="9";
            }
            if (temp.equals("1010")) {
                temp="A";
            }
            if (temp.equals("1011")) {
                temp="B";
            }
            if (temp.equals("1100")) {
                temp="C";
            }
            if (temp.equals("1101")) {
                temp="D";
            }
            if (temp.equals("1110")) {
                temp="E";
            }
            if (temp.equals("1111")) {
                temp="F";
            }
            retValue=retValue+temp;
            i=i+4;
            j++;

        }
        return retValue;




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

    public static void function(ArrayList<String> instructions) throws IOException {
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
            wToOFile(binaryToHex(temp));
            System.out.println(binaryToHex(temp));

        }
    }

    private static void wToOFile(String bff) throws IOException{    //write output to file

        try {
            if(first){
                writer = new BufferedWriter(new FileWriter("outputLbL.txt",false));     //write instructions line by line
                writer2 = new BufferedWriter(new FileWriter("outputOL.txt",false));     //write instructions consecutive
                writer.write("v2.0 raw\n");
                writer2.write("v2.0 raw\n");
                first = false;
            }else{
                writer = new BufferedWriter(new FileWriter("outputLbL.txt",true));
                writer2 = new BufferedWriter(new FileWriter("outputOL.txt",true));
            }
            writer.write(bff+"\n");
            writer2.write(bff+" ");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) try { writer.close(); } catch (IOException ignore) {}
            if (writer2 != null) try { writer2.close(); } catch (IOException ignore) {}
        }

    }

}








