package ctask10_des;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Ctask10_Des {
///////////////1627054
    //////////////omar_mohamed_aboelmakarim
    public static void main(String[] args) {
        String key = "";
       
        try {
            File file = new File("key.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                key = myReader.nextLine();
//                System.out.println("nn" + key);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
         key=hextoBin(key);

//        String binAddr = Integer.toBinaryString(Integer.parseInt(hexAddr, 16)); 
        String b_key = key;
//        String b_key=key;
//        for (int i = 0; i < key.length(); i++) {
//            b_key += StringToBinary(key.charAt(i));
//        }
        int pc_1[] = {57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4};

        String key_pc1 = "";
        System.out.println(b_key.length());
        for (int i = 0; i < pc_1.length; i++) {
            key_pc1 += b_key.charAt(pc_1[i] - 1);
        }
        System.out.println(key_pc1); 

        DesKey k[] = new DesKey[16];
        for (int i = 0; i < 16; i++) {
            k[i] = new DesKey();
        }

        String c = key_pc1.substring(0, key_pc1.length() / 2);
        String D = key_pc1.substring(key_pc1.length() / 2, key_pc1.length());
        System.out.println(c);
        System.out.println(D);

        for (int i = 0; i < 16; i++) {
            System.out.println("left cicular shift");
            c = LCS(c, i);
            D = LCS(D, i);
            k[i].setC(c);
            k[i].setD(D);
            k[i].setKey(c + D);
            System.out.println(k[i].getKey());
        }
        int pc_2[] = {14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32};

        for (int i = 0; i < 16; i++) {
            String key_pc2 = "";
            String skey = k[i].getKey();
            for (int j = 0; j < pc_2.length; j++) {
                key_pc2 += skey.charAt(pc_2[j] - 1);
            }
            k[i].setKey(key_pc2);
        }

        //////////////////////////////////////Encrypt Massage
        int IP[] = {58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7};

        int E[] = {32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1};

        int[] P = {16, 7, 20, 21,
            29, 12, 28, 17,
            1, 15, 23, 26,
            5, 18, 31, 10,
            2, 8, 24, 14,
            32, 27, 3, 9,
            19, 13, 30, 6,
            22, 11, 4, 25
        };
        int[] inv_P = {40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25
        };

        int s1[][] = {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
        {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
        {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
        {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};

        int s2[][] = {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
        {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
        {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
        {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};

        int s3[][] = {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8}, // S3
        {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
        {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
        {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};

        int s4[][] = {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15}, // S4
        {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
        {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
        {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};

        int s5[][] = {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9}, // S5
        {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
        {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
        {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3,}};

        int s6[][] = {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11}, // S6
        {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
        {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
        {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}};

        int s7[][] = {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1}, // S7
        {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
        {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
        {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};

        int s8[][] = {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7}, // S8
        {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
        {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
        {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}

        };
        
        String M = "02468aceeca86420";
        M=hextoBin(M);
        String M_IP = "";
        for (int i = 0; i < IP.length; i++) {
            M_IP += M.charAt(IP[i] - 1);
        }

        Des d[] = new Des[17];
        for (int i = 0; i < 17; i++) {
            d[i] = new Des();
        }
        String L = M_IP.substring(0, M_IP.length() / 2);
        String R = M_IP.substring(M_IP.length() / 2, M_IP.length());
        String R6[] = new String[8];
        System.out.println("L : " + L);
        System.out.println("R : " + R);
        d[0].setL(L);
        d[0].setR(R);

        for (int i = 1; i < 17; i++) {
            d[i].setL(d[i - 1].getR());
            String ER = E_R(d[i - 1].getR(), E);
//            System.out.println("ER : " + ER);
//            System.out.println("Key : " + k[i - 1].getKey());
            String Rk = Xor(ER, k[i - 1].getKey());
//            System.out.println("RK : " + Rk);
            R6 = sR(Rk);
            R6[0] = mR(R6[0], s1);
            R6[1] = mR(R6[1], s2);
            R6[2] = mR(R6[2], s3);
            R6[3] = mR(R6[3], s4);
            R6[4] = mR(R6[4], s5);
            R6[5] = mR(R6[5], s6);
            R6[6] = mR(R6[6], s7);
            R6[7] = mR(R6[7], s8);
            String R4 = "";
            for (int j = 0; j < 8; j++) {
                R4 += R6[j];
            }
//            System.out.println("R4 : " + R4);
            String PR = E_R(R4, P);
            String lastR = Xor(PR, d[i - 1].getL());
//            System.out.println("Last R : " + lastR);
            d[i].setR(lastR);
            System.out.println();
            System.out.println("l: " + binToHex(d[i].getL()));
            System.out.println("r: " + binToHex(d[i].getR()));
            
        }

        String cipher = (new StringBuilder()).append(d[16].getR()).append(d[16].getL()).toString();
        cipher = E_R(cipher, inv_P);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
            FileWriter myWriter = new FileWriter("keywrite.txt");
            for (int i = 0; i < 16; i++) {
                System.out.println("c" + (i + 1) + ":  " + k[i].getC());
                myWriter.write("c" + (i + 1) + ":  " + k[i].getC());
                myWriter.write(System.lineSeparator());
                System.out.println("D" + (i + 1) + ":  " + k[i].getD());
                myWriter.write("D" + (i + 1) + ":  " + k[i].getD());
                myWriter.write(System.lineSeparator());
                System.out.println("the key " + (i + 1) + ":  " + k[i].getKey());
                myWriter.write("the key " + (i + 1) + ":  " + k[i].getKey());
                myWriter.write(System.lineSeparator());
                myWriter.write("-------------------------------------------------------");
                myWriter.write(System.lineSeparator());
            }
            myWriter.write("the Encrypt massage " + cipher);
            myWriter.write(System.lineSeparator());
            System.out.println("the Encrypt massage " + binToHex(cipher));
            myWriter.close();

        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    static String StringToBinary(char s) {
//        int n = s.length();
        String bin = "";
        int val = Integer.valueOf(s);
        bin = "";
        while (val > 0) {
            if (val % 2 == 1) {
                bin += '1';
            } else {
                bin += '0';
            }
            val /= 2;
        }
        bin = reverse(bin);

        return bin;
    }

    static String reverse(String input) {
        char[] a = input.toCharArray();
        int l, r = 0;
        r = a.length - 1;

        for (l = 0; l < r; l++, r--) {
            // Swap values of l and r
            char temp = a[l];
            a[l] = a[r];
            a[r] = temp;
        }
        return String.valueOf(a);
    }

    static String LCS(String a, int index) {

        if (index == 0 || index == 1 || index == 8 || index == 15) {
            char f = a.charAt(0);
            String m = a.substring(1, a.length());
            a = "";
            a = m + f;
        } else {
            String mf = a.substring(0, 2);
            String m = a.substring(2, a.length());
            a = "";
            a = m + mf;
        }
        return a;
    }

    static String E_R(String R, int E[]) {
        String ER = "";
        for (int i = 0; i < E.length; i++) {
            ER += R.charAt(E[i] - 1);
        }

        return ER;

    }

    static String Xor(String R, String K) {
        String EK = "";
        for (int i = 0; i < R.length(); i++) {
            if (R.charAt(i) == K.charAt(i)) {
                EK += "0";
            } else {
                EK += "1";
            }
        }

        return EK;

    }

    static String mR(String R, int s[][]) {
        String mR = "";
//        String row= R.charAt(0) + R.charAt(R.length()-1);
        String row = (new StringBuilder()).append(R.charAt(0)).append(R.charAt(R.length() - 1)).toString();
        int r = Integer.parseInt(row, 2);
        String column = R.substring(1, R.length() - 1);
        int c = Integer.parseInt(column, 2);
        mR = Integer.toBinaryString(s[r][c]);
        mR = String.format("%4s", mR).replaceAll(" ", "0");
        return mR;

    }

    static String[] sR(String s) {
        String R6[] = new String[8];
        for (int i = 0; i < 8; i++) {
            R6[i] = s.substring(i * 6, 6 * (i + 1));
        }

        return R6;
    }

    private String hexToBin(String hex) {
        String bin = "";
        String binFragment = "";
        int iHex;
        hex = hex.trim();
        hex = hex.replaceFirst("0x", "");

        for (int i = 0; i < hex.length(); i++) {
            iHex = Integer.parseInt("" + hex.charAt(i), 16);
            binFragment = Integer.toBinaryString(iHex);

            while (binFragment.length() < 4) {
                binFragment = "0" + binFragment;
            }
            bin += binFragment;
        }
        return bin;
    }

   static String hextoBin(String input) {
        int n = input.length() * 4;
        input = Long.toBinaryString(
                Long.parseUnsignedLong(input, 16));
        while (input.length() < n) {
            input = "0" + input;
        }
        return input;
    }

    // binary to hexadecimal conversion
   static String binToHex(String input) {
        int n = (int) input.length() / 4;
        input = Long.toHexString(
                Long.parseUnsignedLong(input, 2));
        while (input.length() < n) {
            input = "0" + input;
        }
        return input;
    }

}
