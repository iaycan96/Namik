public class Operations {
    public String convert(String src){
        // int decimal = Integer.parseInt(binaryStr,2);
        // String hexStr = Integer.toString(decimal,16);
        String a=src.substring(0,4);
        int decimal1 = Integer.parseInt(a,2);
        String hexStr1 = Integer.toString(decimal1,16);

        String b=src.substring(4,8);
        int decimal2 = Integer.parseInt(b,2);
        String hexStr2 = Integer.toString(decimal2,16);

        String c=src.substring(8,12);
        int decimal3 = Integer.parseInt(c,2);
        String hexStr3 = Integer.toString(decimal3,16);

        String d=src.substring(12,16);
        int decimal4 = Integer.parseInt(d,2);
        String hexStr4 = Integer.toString(decimal4,16);

        String e=src.substring(16,20);
        int decimal5 = Integer.parseInt(e,2);
        String hexStr5 = Integer.toString(decimal5,16);

        return hexStr1+hexStr2+hexStr3+hexStr4+hexStr5;
    }


    public String ExtensionControl(String src, int bitNumber) {
        int length = src.length();
        String exten;
        if (length < bitNumber) {
            int a = bitNumber - length;
            char zero = '0';
            char[] repeat = new char[a];
            java.util.Arrays.fill(repeat, zero);
            exten = new String(repeat) + src;
            return exten;

        } else {
            return src;
        }

    }

    public String twoComplement(String src, int bitnumber) { // 32 bitlik giriyor ve istenilen bit number da digitle çıkıyor
        int length = src.length(); // 32 bit
        int beginIndex = length - bitnumber;
        String exten = src.substring(beginIndex);
        return exten;
    }

    public String ADD(int dest, int src1, int src2) {
        String opcode = "0000";
        String dest_bin = Integer.toBinaryString(dest); // 4 bit olmalı
        String src1_bin = Integer.toBinaryString(src1); // 4 bit olmalı
        String src2_bin = Integer.toBinaryString(src2); // 4 bit olmalı
        String dontcarry = "0000";
        String inst = opcode+ ExtensionControl(dest_bin, 4) +ExtensionControl(src1_bin, 4) + ExtensionControl(src2_bin, 4) + dontcarry;
        return inst;
    }

    public String ADDI(int dest, int src1, int imm) {
        String opcode = "0001";
        String dest_bin = Integer.toBinaryString(dest); // 4 bit
        String src1_bin = Integer.toBinaryString(src1); // 4 bit
        if( imm<0){
            String imm_bin = Integer.toBinaryString(imm); // 8 bit olmalı
            String inst = opcode +ExtensionControl(dest_bin, 4) + ExtensionControl(src1_bin, 4) +  twoComplement(imm_bin, 8);
            return inst;
        }else {
            String imm_bin = Integer.toBinaryString(imm); // 8 bit olmalı
            String inst = opcode +  ExtensionControl(dest_bin, 4) +  ExtensionControl(src1_bin, 4) + ExtensionControl(imm_bin, 8);
            return inst;
        }
    }

    public String OR(int dest, int src1, int src2) {
        String opcode = "0100";
        String dest_bin = Integer.toBinaryString(dest); // 4 bit olmalı
        String src1_bin = Integer.toBinaryString(src1); // 4 bit olmalı
        String src2_bin = Integer.toBinaryString(src2); // 4 bit olmalı
        String dontcarry = "0000";
        String inst = opcode + ExtensionControl(dest_bin, 4) +  ExtensionControl(src1_bin, 4) +  ExtensionControl(src2_bin, 4) +  dontcarry;
        return inst;
    }

    public String ORI(int dest, int src1, int imm) {
        String opcode = "0101";
        String dest_bin = Integer.toBinaryString(dest); // 4 bit
        String src1_bin = Integer.toBinaryString(src1); // 4 bit
        if(imm<0){
            String imm_bin = Integer.toBinaryString(imm); // 8 bit olmalı
            String inst = opcode +  ExtensionControl(dest_bin, 4) +  ExtensionControl(src1_bin, 4) +  twoComplement(imm_bin, 8);
            return inst;
        }else {
            String imm_bin = Integer.toBinaryString(imm); // 8 bit olmalı
            String inst = opcode +  ExtensionControl(dest_bin, 4) + ExtensionControl(src1_bin, 4) +  ExtensionControl(imm_bin, 8);
            return inst;
        }
    }

    public String XOR(int dest, int src1, int src2) {
        String opcode = "0110";
        String dest_bin = Integer.toBinaryString(dest); // 4 bit olmalı
        String src1_bin = Integer.toBinaryString(src1); // 4 bit olmalı
        String src2_bin = Integer.toBinaryString(src2); // 4 bit olmalı
        String dontcarry = "0000";
        String inst = opcode +  ExtensionControl(dest_bin, 4) + ExtensionControl(src1_bin, 4) + ExtensionControl(src2_bin, 4) +  dontcarry;
        return inst;
    }

    public String XORI(int dest, int src1, int imm) {
        String opcode = "0111";
        String dest_bin = Integer.toBinaryString(dest); // 4 bit
        String src1_bin = Integer.toBinaryString(src1); // 4 bit
        if(imm<0){
            String imm_bin = Integer.toBinaryString(imm); // 8 bit olmalı
            String inst = opcode + ExtensionControl(dest_bin, 4) +  ExtensionControl(src1_bin, 4) + twoComplement(imm_bin, 8);
            return inst;
        }else {
            String imm_bin = Integer.toBinaryString(imm); // 8 bit olmalı
            String inst = opcode + ExtensionControl(dest_bin, 4) +  ExtensionControl(src1_bin, 4) + ExtensionControl(imm_bin, 8);
            return inst;
        }
    }

    public String ST(int src, int addr) {
        String opcode = "1010";
        String dest_bin = Integer.toBinaryString(src); // 4 bit olmalı
        if (addr < 0) {
            String src1_bin = Integer.toBinaryString(addr);
            String inst = opcode + ExtensionControl(dest_bin, 4) +  twoComplement(src1_bin, 12);
            return inst;
        } else {
            String src1_bin = Integer.toBinaryString(addr); // 12 bit olmalı
            String inst = opcode + ExtensionControl(dest_bin, 4) +  ExtensionControl(src1_bin, 12);
            return inst;
        }
    }

    public String JUMP(int addr) {
        String opcode = "1000";
        if (addr < 0) {
            String dest_bin = Integer.toBinaryString(addr); //32 bit
            String inst = opcode + twoComplement(dest_bin, 16); // two complement 1 extension
            return inst;

        } else {
            String dest_bin = Integer.toBinaryString(addr); //16 bit olmalı
            String inst = opcode +  ExtensionControl(dest_bin, 16); // 0 extension
            return inst;
        }
    }

    public String LD(int dest, int addr) {
        String opcode = "1001";
        String dest_bin = Integer.toBinaryString(dest); // 4 bit olmalı
        if (addr<0) {
            String src1_bin = Integer.toBinaryString(addr);
            String inst = opcode +  ExtensionControl(dest_bin, 4) +  twoComplement(src1_bin, 12);
            return inst;
        } else {
            String src1_bin = Integer.toBinaryString(addr); // 12 bit olmalı   POZİTİF DÜŞÜNDÜM
            String inst = opcode + ExtensionControl(dest_bin, 4) +  ExtensionControl(src1_bin, 12);
            return inst;
        }
    }
    public String AND(int dest, int src1, int src2) {
        String opcode = "0010";
        String dest_bin = Integer.toBinaryString(dest); // 4 bit olmalı
        String src1_bin = Integer.toBinaryString(src1); // 4 bit olmalı
        String src2_bin = Integer.toBinaryString(src2); // 4 bit olmalı
        String dontcarry = "0000";
        String inst = opcode +  ExtensionControl(dest_bin, 4) +  ExtensionControl(src1_bin, 4) +  ExtensionControl(src2_bin, 4) +  dontcarry;
        return inst;

    }

    public String ANDI(int dest, int src1, int imm) {
        String opcode = "0011";
        String dest_bin = Integer.toBinaryString(dest); // 4 bit
        String src1_bin = Integer.toBinaryString(src1); // 4 bit
        if (imm<0) {
            String imm_bin = Integer.toBinaryString(imm); // 8 bit olmalı
            String inst = opcode + ExtensionControl(dest_bin, 4) + ExtensionControl(src1_bin, 4) + twoComplement(imm_bin, 8);
            return inst;
        } else {
            String imm_bin = Integer.toBinaryString(imm); // 8 bit olmalı
            String inst = opcode +  ExtensionControl(dest_bin, 4) +  ExtensionControl(src1_bin, 4) +  ExtensionControl(imm_bin, 8);
            return inst;
        }
    }

    public String BEQ(int op1, int op2, int addr) {
        String opcode = "1011";
        String nzp = "010"; // 3 bit
        String op1_bin = Integer.toBinaryString(op1); // 4 bit
        String op2_bin = Integer.toBinaryString(op2); // 4 bit
        if (addr < 0) {
            String addr_bin = Integer.toBinaryString(addr); // 5 bit
            String inst = opcode + ExtensionControl(op1_bin, 4) + ExtensionControl(op2_bin, 4) +  twoComplement(addr_bin, 5) + nzp;
            return inst;
        } else {
            String addr_bin = Integer.toBinaryString(addr); // 5 bit
            String inst = opcode + ExtensionControl(op1_bin, 4) + ExtensionControl(op2_bin, 4) + ExtensionControl(addr_bin, 5) + nzp;
            return inst;
        }
    }

    public String BLT(int op1, int op2, int addr) {
        String opcode = "1100";
        String nzp = "100";
        String op1_bin = Integer.toBinaryString(op1); // 4 bit
        String op2_bin = Integer.toBinaryString(op2); // 4 bit
        if(addr<0){
            String addr_bin = Integer.toBinaryString(addr); // 5 bit
            String inst = opcode +ExtensionControl(op1_bin, 4) + ExtensionControl(op2_bin, 4) +twoComplement(addr_bin, 5) + nzp;
            return inst;
        }else {
            String addr_bin = Integer.toBinaryString(addr); // 5 bit
            String inst = opcode +  ExtensionControl(op1_bin, 4) +  ExtensionControl(op2_bin, 4) + ExtensionControl(addr_bin, 5) + nzp;
            return inst;
        }
    }

    public String BGT(int op1, int op2, int addr) {
        String opcode = "1101";
        String nzp = "001";
        String op1_bin = Integer.toBinaryString(op1); // 4 bit
        String op2_bin = Integer.toBinaryString(op2); // 4 bit
        if (addr<0) {
            String addr_bin = Integer.toBinaryString(addr); // 5 bit
            String inst = opcode + ExtensionControl(op1_bin, 4) + ExtensionControl(op2_bin, 4) + twoComplement(addr_bin, 5) + nzp;
            return inst;
        } else {
            String addr_bin = Integer.toBinaryString(addr); // 5 bit
            String inst = opcode + ExtensionControl(op1_bin, 4) + ExtensionControl(op2_bin, 4) +  ExtensionControl(addr_bin, 5) + nzp;
            return inst;
        }
    }
    public String BLE(int op1, int op2, int addr) {
        String opcode = "1110";
        String nzp = "110";
        String op1_bin = Integer.toBinaryString(op1); // 4 bit
        String op2_bin = Integer.toBinaryString(op2); // 4 bit
        if (addr < 0) {
            String addr_bin = Integer.toBinaryString(addr); // 5 bit
            String inst = opcode + ExtensionControl(op1_bin, 4) +  ExtensionControl(op2_bin, 4) +  twoComplement(addr_bin, 5) + nzp;
            return inst;

        } else {
            String addr_bin = Integer.toBinaryString(addr); // 5 bit
            String inst = opcode +  ExtensionControl(op1_bin, 4) +  ExtensionControl(op2_bin, 4) +  ExtensionControl(addr_bin, 5) + nzp;
            return inst;
        }
    }

    public String BGE(int op1, int op2, int addr) {
        String opcode = "1111";
        String nzp = "011";
        String op1_bin = Integer.toBinaryString(op1); // 4 bit
        String op2_bin = Integer.toBinaryString(op2); // 4 bit
        if (addr < 0) {
            String addr_bin = Integer.toBinaryString(addr); // 5 bit
            String inst = opcode + ExtensionControl(op1_bin, 4) + ExtensionControl(op2_bin, 4) + twoComplement(addr_bin, 5) + nzp;
            return inst;
        } else {
            String addr_bin = Integer.toBinaryString(addr); // 5 bit
            String inst = opcode + ExtensionControl(op1_bin, 4) +  ExtensionControl(op2_bin, 4) + ExtensionControl(addr_bin, 5) + nzp;
            return inst;
        }
    }
}


