package com.ztt.algorithm.bitwise;

import java.text.DecimalFormat;

/**
 *  打印二进制
 */
public class BitwiseLogicalOperators {

   /* public static void printBinaryStr(int integer) {
        String binaryStr = Integer.toBinaryString(integer);

        String newString = String.format("%0" + (32 - binaryStr.length()) + "d", 0) + binaryStr;

        StringBuilder str = new StringBuilder(newString);
        int i = str.length() / 4;
        int j = str.length() % 4;

        for (int x = (j == 0 ? i - 1 : i); x > 0; x--) {
            str = str.insert(x * 4," ");
        }

        System.out.println(str.toString());

    }*/

    public static void printBinaryStr(int integer) {
        String binaryStr = Integer.toBinaryString(integer);

        String newString = String.format("%0" + (32 - binaryStr.length()) + "d", 0) + binaryStr;

        int i = newString.length() / 4;

        StringBuffer stringBuffer = new StringBuffer();
        for (int x = 0; x < i; x++) {
            String s = newString.substring(x * 4, (x + 1) * 4);
            stringBuffer.append(s).append(" ");
        }

        System.out.println(stringBuffer.toString());

    }


    public static void bit() {
        int a = -5;//101;
        printBinaryStr(~a);
    }

    public static void main(String[] args) {
        BitwiseLogicalOperators.bit();

    }
}
