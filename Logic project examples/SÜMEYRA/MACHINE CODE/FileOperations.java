import java.io.*;


public class FileOperations{


    public static void main(String []args)throws Exception {
        Operations operator = new Operations();
        Operations operator1 = new Operations();
        // read from file

        BufferedReader br = null;
        String readLine;

        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Lenovo\\Desktop\\input.txt"));
        } catch (FileNotFoundException fnf) {
            System.out.println(fnf.getMessage() + "File is not found");
            System.exit(0);
        }
        // write to file

            BufferedWriter bw =null;
            bw=new BufferedWriter(new FileWriter("C:\\Users\\Lenovo\\Desktop\\output.txt"));

            //bw.close();

        try {
            while ((readLine = br.readLine()) != null) {
                int d = readLine.indexOf(' ');
                String opcode = readLine.substring(0, d); // opcode için boşluğa kadar oku al sonra kalanları virgüle göre ayır al
                String dest = readLine.substring(d + 1);
                //System.out.println(opcode);
                //System.out.println(dest);

                if (opcode.equals("ADD")) {
                    String[] dest1 = dest.split(",");
                    //String brr = opcode + " " + dest1[0] + " " + dest1[1] + " " + dest1[2] + " ";
                    String destination = dest1[0].substring(1);
                    int destValue = Integer.parseInt(destination);
                    String source1 = dest1[1].substring(1);
                    int src1Value = Integer.parseInt(source1);
                    String source2 = dest1[2].substring(1);
                    int src2Value = Integer.parseInt(source2);
                    String brr = operator.ADD(destValue, src1Value, src2Value); // instruction binary production
                    String brr1 = operator1.convert(brr);
                    //System.out.println(brr1);
                    bw.write(brr1);

                    bw.newLine();


                } else if (opcode.equals("OR")) {
                    String[] dest1 = dest.split(",");
                    // String brr = opcode + " " + dest1[0] + " " + dest1[1] + " " + dest1[2] + " ";
                    String destination = dest1[0].substring(1);
                    int destValue = Integer.parseInt(destination);
                    String source1 = dest1[1].substring(1);
                    int src1Value = Integer.parseInt(source1);
                    String source2 = dest1[2].substring(1);
                    int src2Value = Integer.parseInt(source2);
                    String brr = operator.OR(destValue, src1Value, src2Value); // instruction binary production
                    String brr1 = operator1.convert(brr);
                    bw.write(brr1);
                    bw.newLine();
                    //readLine = br.readLine();


                } else if (opcode.equals("XOR")) {
                    String[] dest1 = dest.split(",");
                    // String brr = opcode + " " + dest1[0] + " " + dest1[1] + " " + dest1[2] + " ";
                    String destination = dest1[0].substring(1);
                    int destValue = Integer.parseInt(destination);
                    String source1 = dest1[1].substring(1);
                    int src1Value = Integer.parseInt(source1);
                    String source2 = dest1[2].substring(1);
                    int src2Value = Integer.parseInt(source2);
                    String brr = operator.XOR(destValue, src1Value, src2Value); // instruction binary production
                    String brr1 = operator1.convert(brr);
                    bw.write(brr1);
                    bw.newLine();
                    //readLine = br.readLine();

                } else if (opcode.equals("AND")) {
                    String[] dest1 = dest.split(",");
                    //String brr = opcode + " " + dest1[0] + " " + dest1[1] + " " + dest1[2] + " ";
                    String destination = dest1[0].substring(1);
                    int destValue = Integer.parseInt(destination);
                    String source1 = dest1[1].substring(1);
                    int src1Value = Integer.parseInt(source1);
                    String source2 = dest1[2].substring(1);
                    int src2Value = Integer.parseInt(source2);
                    String brr = operator.AND(destValue, src1Value, src2Value); // instruction binary production
                    String brr1 = operator1.convert(brr);
                    bw.write(brr1);
                    bw.newLine();
                    //readLine = br.readLine();

                } else if (opcode.equals("ADDI")) {
                    //String brr = opcode + " " + dest1[0] + " " + dest1[1] + " " + imm + " ";
                    String[] dest1 = dest.split(",");
                    String destination = dest1[0].substring(1);
                    int destValue = Integer.parseInt(destination);
                    String source1 = dest1[1].substring(1);
                    int src1Value = Integer.parseInt(source1);
                    String imm = dest1[2].substring(1);
                    int immValue = Integer.parseInt(imm);
                    String brr = operator.ADDI(destValue, src1Value, immValue);
                    String brr1 = operator1.convert(brr);
                    bw.write(brr1);
                    bw.newLine();
                    //readLine = br.readLine();

                } else if (opcode.equals("ORI")) {
                    //String brr = opcode + " " + dest1[0] + " " + dest1[1] + " " + imm + " ";
                    String[] dest1 = dest.split(",");
                    String destination = dest1[0].substring(1);
                    int destValue = Integer.parseInt(destination);
                    String source1 = dest1[1].substring(1);
                    int src1Value = Integer.parseInt(source1);
                    String imm = dest1[2].substring(1);
                    int immValue = Integer.parseInt(imm);
                    String brr = operator.ORI(destValue, src1Value, immValue);
                    String brr1 = operator1.convert(brr);
                    bw.write(brr1);
                    bw.newLine();
                    //readLine = br.readLine();

                } else if (opcode.equals("XORI")) {
                    String[] dest1 = dest.split(",");
                    String destination = dest1[0].substring(1);
                    int destValue = Integer.parseInt(destination);
                    String source1 = dest1[1].substring(1);
                    int src1Value = Integer.parseInt(source1);
                    String imm = dest1[2].substring(1);
                    int immValue = Integer.parseInt(imm);
                    //String brr = opcode + " " + dest1[0] + " " + dest1[1] + " " + imm + " ";
                    String brr = operator.XORI(destValue, src1Value, immValue);
                    String brr1 = operator1.convert(brr);
                    bw.write(brr1);
                    bw.newLine();
                    //readLine = br.readLine();

                } else if (opcode.equals("ANDI")) {
                    String[] dest1 = dest.split(",");
                    String destination = dest1[0].substring(1);
                    int destValue = Integer.parseInt(destination);
                    String source1 = dest1[1].substring(1);
                    int src1Value = Integer.parseInt(source1);
                    String imm = dest1[2].substring(1);
                    int immValue = Integer.parseInt(imm);
                    // String brr = opcode + " " + dest1[0] + " " + dest1[1] + " " + imm + " ";
                    String brr = operator.ANDI(destValue, src1Value, immValue);
                    String brr1 = operator1.convert(brr);
                    bw.write(brr1);
                    bw.newLine();
                    //readLine = br.readLine();

                } else if (opcode.equals("BEQ")) {
                    String[] dest1 = dest.split(",");
                    String destination = dest1[0].substring(1);
                    int destValue = Integer.parseInt(destination);
                    String source1 = dest1[1].substring(1);
                    int src1Value = Integer.parseInt(source1);
                    String addr = dest1[2].substring(1);
                    int addrValue = Integer.parseInt(addr);
                    //String brr = opcode + " " + dest1[0] + " " + dest1[1] + " " + imm + " ";
                    String brr = operator.BEQ(destValue, src1Value, addrValue);
                    String brr1 = operator1.convert(brr);
                    bw.write(brr1);
                    bw.newLine();
                    //readLine = br.readLine();

                } else if (opcode.equals("BLT")) {
                    String[] dest1 = dest.split(",");
                    String destination = dest1[0].substring(1);
                    int destValue = Integer.parseInt(destination);
                    String source1 = dest1[1].substring(1);
                    int src1Value = Integer.parseInt(source1);
                    String addr = dest1[2].substring(1);
                    int addrValue = Integer.parseInt(addr);
                    //String brr = opcode + " " + dest1[0] + " " + dest1[1] + " " + imm + " ";
                    String brr = operator.BLT(destValue, src1Value, addrValue);
                    String brr1 = operator1.convert(brr);
                    bw.write(brr1);
                    bw.newLine();
                    // readLine = br.readLine();

                } else if (opcode.equals("BGT")) {
                    String[] dest1 = dest.split(",");
                    String destination = dest1[0].substring(1);
                    int destValue = Integer.parseInt(destination);
                    String source1 = dest1[1].substring(1);
                    int src1Value = Integer.parseInt(source1);
                    String addr = dest1[2].substring(1);
                    int addrValue = Integer.parseInt(addr);
                    // String brr = opcode + " " + dest1[0] + " " + dest1[1] + " " + imm + " ";
                    String brr = operator.BGT(destValue, src1Value, addrValue);
                    String brr1 = operator1.convert(brr);
                    bw.write(brr1);
                    bw.newLine();
                    //readLine = br.readLine();

                } else if (opcode.equals("BLE")) {
                    String[] dest1 = dest.split(",");
                    String destination = dest1[0].substring(1);
                    int destValue = Integer.parseInt(destination);
                    String source1 = dest1[1].substring(1);
                    int src1Value = Integer.parseInt(source1);
                    String addr = dest1[2].substring(1);
                    int addrValue = Integer.parseInt(addr);
                    //String brr = opcode + " " + dest1[0] + " " + dest1[1] + " " + imm + " ";
                    String brr = operator.BLE(destValue, src1Value, addrValue);
                    String brr1 = operator1.convert(brr);
                    bw.write(brr1);
                    bw.newLine();
                    //readLine = br.readLine();

                } else if (opcode.equals("BGE")) {
                    String[] dest1 = dest.split(",");
                    String destination = dest1[0].substring(1);
                    int destValue = Integer.parseInt(destination);
                    String source1 = dest1[1].substring(1);
                    int src1Value = Integer.parseInt(source1);
                    String addr = dest1[2].substring(1);
                    int addrValue = Integer.parseInt(addr);
                    //String brr = opcode + " " + dest1[0] + " " + dest1[1] + " " + imm + " ";
                    String brr = operator.BGE(destValue, src1Value, addrValue);
                    String brr1 = operator1.convert(brr);
                    bw.write(brr1);
                    bw.newLine();
                    //readLine = br.readLine();


                } else if (opcode.equals("ST")) {
                    String[] dest1 = dest.split(",");
                    String src = dest1[0].substring(1);
                    int srcValue = Integer.parseInt(src);
                    String addr = dest1[1].substring(1);
                    int addrValue = Integer.parseInt(addr);
                    //String brr = opcode + " " + dest1[0] + " " + addr;
                    String brr = operator.ST(srcValue, addrValue);
                    String brr1 = operator1.convert(brr);
                    bw.write(brr1);
                    bw.newLine();
                    //readLine = br.readLine();

                } else if (opcode.equals("LD")) {
                    String[] dest1 = dest.split(",");
                    String regsrc = dest1[0].substring(1);
                    int regValue = Integer.parseInt(regsrc);
                    String addr = dest1[1].substring(1);
                    int addrValue = Integer.parseInt(addr);
                    // String brr = opcode + " " + dest1[0] + " " + addr;
                    String brr = operator.LD(regValue, addrValue);
                    String brr1 = operator1.convert(brr);
                    bw.write(brr1);
                    bw.newLine();
                    //readLine = br.readLine();

                } else if (opcode.equals("JUMP")) {
                    String addr = dest.substring(1);
                    int addrValue = Integer.parseInt(addr);
                    // String brr = opcode + addr;
                    String brr = operator.JUMP(addrValue);
                    String brr1 = operator1.convert(brr);
                    bw.write(brr1);
                    bw.newLine();
                    //rea dLine =br.readLine();
                }

            }

        } catch (IOException ioex) {
            System.out.println(ioex.getMessage() + "Error reading file");
        }
        finally {
            bw.close();
            System.exit(0);
        }



    }
    }


