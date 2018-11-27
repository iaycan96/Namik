import java.io.*;
import java.util.Scanner;
import java.lang.String;

public class Assembler{

    private static final int inssa = 20;     //instruction bit limit
    private static File input = new File("input.txt");      //load input file
    private static Writer writer,writer2;
    private static Scanner fileScanner;
    private static boolean first = true;
    private static String[] parts;
    private static String bff = "";
    private static String add="";
    private static int ofst=0;

    public static void main(String[] args) throws IOException {

        fileScanner = new Scanner(input);

        while(getParts()){
            bff = writeOpcode(parts[0]);

            for(int i = 1; i < parts.length; i++){
                if(!(parts[i].equals(""))) bff = wRegCode(parts[i], bff);
            }

            int bffLnt = bff.length();

            for(int i = 0; i < inssa-bffLnt; i++){
                bff = bff + "0";
            }

            wToOFile(bff);
        }

    }

    private static boolean getParts() throws IOException{    //get parts by space and comma characters

        if(!(fileScanner.hasNextLine())) return false;      //if EOF, stop reading

        String inputIns = fileScanner.nextLine();
        parts = inputIns.split("\\s+|,");   //split by space and comma characters

        return true;
    }

    private static String writeOpcode(String op){   //converts opcode names to binary format

        bff = "";
        add="";
        if(op.equalsIgnoreCase("ld")) {
            bff += "0000";
            add="0";
            ofst=10;
        }else if(op.equalsIgnoreCase("xor")){
            bff += "0001";
        }else if(op.equalsIgnoreCase("or")){
            bff += "0010";
        }else if(op.equalsIgnoreCase("and")){
            bff += "0011";
        }else if(op.equalsIgnoreCase("add")){
            bff += "0100";
        }else if(op.equalsIgnoreCase("addi")){
            bff += "0101";
            ofst=8;
        }else if(op.equalsIgnoreCase("ori")){
            bff += "0110";
            ofst=8;
        }else if(op.equalsIgnoreCase("xori")){
            bff += "0111";
            ofst=8;
        }else if(op.equalsIgnoreCase("andi")){
            bff += "1000";
            ofst=8;
        }else if(op.equalsIgnoreCase("bgt")){
            bff += "1001";
            ofst=8;
        }else if(op.equalsIgnoreCase("beq")){
            bff += "1010";
            ofst=8;
        }else if(op.equalsIgnoreCase("bge")){
            bff += "1011";
            ofst=8;
        }else if(op.equalsIgnoreCase("blt")){
            bff += "1100";
            ofst=8;
        }else if(op.equalsIgnoreCase("jump")){
            bff += "1101";
            ofst=16;
        }else if(op.equalsIgnoreCase("ble")){
            bff += "1110";
            ofst=8;
        }else if(op.equalsIgnoreCase("st")){
            bff += "1111";
            ofst=10;
        }else{
            System.out.println("ERROR: Wrong operation name.");
            System.exit(0);
        }

        return bff;
    }

    private static String wRegCode(String dst,String bff){

        char spc = dst.charAt(0);

        if(spc == 'r' || spc == 'R'){       //if it starts with "R", it means there is a register
            String sp = dst.substring(1);
            dst = Integer.toBinaryString((byte)Integer.parseInt(sp));
            int dstLnt = dst.length();
            StringBuilder dstBuilder = new StringBuilder(dst);
            for (int i = 0; i < 4 - dstLnt; i++) {
                dstBuilder.insert(0, "0");

            }
            dst = dstBuilder.toString();
        }else{
            if(dst.length() >= 2) {
                int spc2 = dst.charAt(1);

                if (spc == '0' && spc2 == 'x') {    //if it starts with "0x", it means there is a hexadecimal number
                    dst = Integer.toBinaryString(Integer.parseInt(dst.substring(2), 16));
                } else if (spc == '0' && spc2 == 'b') {     //if it starts with "0b", it means there is a binary number
                    dst = dst.substring(2);
                }
                else{
                    int val=Integer.parseInt(dst);
                    dst = Integer.toBinaryString(val);
                    if(val<0){
                        dst=dst.substring(32-ofst);
                    }

                }
            }else{  //if the part has only one character, only convert to integer
                dst = Integer.toBinaryString(Integer.parseInt(dst));
            }
            int dstLnt = dst.length(), bffLnt = bff.length();

            StringBuilder dstBuilder = new StringBuilder(dst);

            for(int i = 0; i < inssa - bffLnt - dstLnt; i++){
                dstBuilder.insert(0, "0");
            }
            dst = dstBuilder.toString();
        }

        bff += dst;
        return bff;
    }

    private static void wToOFile(String bff) throws IOException{    //write output to file
        bff = Integer.toHexString(Integer.parseInt(bff,2));
        int lt=bff.length();
        for(int i=0;i<5-lt;i++){
            bff="0"+bff;
        }
        bff = bff+"\n";
        try {
            if(first){
                writer = new BufferedWriter(new FileWriter("outputLbL.txt",false));     //write instructions line by line
                writer2 = new BufferedWriter(new FileWriter("outputOL.txt",false));     //write instructions consecutive
                writer.write("v2.0 raw");
                writer2.write("v2.0 raw");
                writer.write("\r\n");
                first = false;
            }else{
                writer = new BufferedWriter(new FileWriter("outputLbL.txt",true));
                writer2 = new BufferedWriter(new FileWriter("outputOL.txt",true));
            }
            writer.write(bff);
            writer2.write(bff);
            //writer.write("\r\n");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) try { writer.close(); } catch (IOException ignore) {}
            if (writer2 != null) try { writer2.close(); } catch (IOException ignore) {}
        }

    }
    public static int sign_extend(int val, int bits) {
        int shift = 20 - bits;  // int always has 32 bits in Java
        int s = val << shift;
        return s >> shift;
    }
}
