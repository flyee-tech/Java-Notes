package com.peierlong.base;


import static com.peierlong.base.util.Format.formatBinary;

/**
 * 关于位运算的学习
 *
 * @author Peiel
 * @version V1.0
 * @date 2019/12/23
 */
public class BitExercises {

    // <<
    // Signed Left shift operator
    public static void leftShift() {
        int a = 5;
        System.out.println("5 的二进制                  : " + formatBinary(Integer.toBinaryString(a), 32));
        int b = 5 << 2;
        System.out.println("左移2位的结果 的二进制        : " + formatBinary(Integer.toBinaryString(b), 32));

        int c = -5;
        System.out.println("-5 的二进制                 : " + formatBinary(Integer.toBinaryString(c), 32));
        int d = c << 2;
        System.out.println("左移2位的结果 的二进制        : " + formatBinary(Integer.toBinaryString(d), 32));
    }

    // >>
    // Signed Right shift operator
    public static void rightShift() {
        int a = 5;
        System.out.println("5 的二进制                  : " + formatBinary(Integer.toBinaryString(a), 32));
        int b = a >> 2;
        System.out.println("右移2位的结果 的二进制        : " + formatBinary(Integer.toBinaryString(b), 32));

        int c = -5;
        System.out.println("-5 的二进制                 : " + formatBinary(Integer.toBinaryString(c), 32));
        int d = c >> 2;
        System.out.println("右移2位的结果 的二进制        : " + formatBinary(Integer.toBinaryString(d), 32));
    }

    // >>>
    // UnSigned Right shift operator
    public static void unSignedRightShift() {
        int a = 5;
        System.out.println("5 的二进制                  : " + formatBinary(Integer.toBinaryString(a), 32));
        int b = a >>> 2;
        System.out.println("无符号右移2位的结果 的二进制   : " + formatBinary(Integer.toBinaryString(b), 32));

        int c = -5;
        System.out.println("-5 的二进制                 : " + formatBinary(Integer.toBinaryString(c), 32));
        int d = c >>> 2;
        System.out.println("无符号右移2位的结果 的二进制   : " + formatBinary(Integer.toBinaryString(d), 32));
    }

    // &
    // Bitwise AND
    public static void bitwiseAND() {
        int a = 5;
        System.out.println("5 的二进制                  : " + formatBinary(Integer.toBinaryString(a), 32));
        int b = 7;
        System.out.println("7 的二进制                  : " + formatBinary(Integer.toBinaryString(b), 32));
        int c = a & b;
        System.out.println("5 & 7 = 的二进制            : " + formatBinary(Integer.toBinaryString(c), 32));
    }

    // |
    // Bitwise OR
    public static void bitwiseOR() {
        int a = 5;
        System.out.println("5 的二进制                  : " + formatBinary(Integer.toBinaryString(a), 32));
        int b = 7;
        System.out.println("7 的二进制                  : " + formatBinary(Integer.toBinaryString(b), 32));
        int c = a | b;
        System.out.println("5 | 7 = 的二进制            : " + formatBinary(Integer.toBinaryString(c), 32));
    }

    // ^
    // Bitwise XOR
    public static void bitwiseXOR() {
        int a = 5;
        System.out.println("5 的二进制                  : " + formatBinary(Integer.toBinaryString(a), 32));
        int b = 7;
        System.out.println("7 的二进制                  : " + formatBinary(Integer.toBinaryString(b), 32));
        int c = a ^ b;
        System.out.println("5 ^ 7 = 的二进制            : " + formatBinary(Integer.toBinaryString(c), 32));
    }

    // ~
    // Bitwise Complement
    public static void bitwiseComplement() {
        int a = 5;
        System.out.println("5 的二进制                  : " + formatBinary(Integer.toBinaryString(a), 32));
        int b = ~a;
        System.out.println("~5的二进制                  : " + formatBinary(Integer.toBinaryString(b), 32));
    }


    public static void main(String[] args) {
        leftShift();
        System.out.println("---");
        rightShift();
        System.out.println("---");
        unSignedRightShift();
        System.out.println("---");
        bitwiseAND();
        System.out.println("---");
        bitwiseOR();
        System.out.println("---");
        bitwiseXOR();
        System.out.println("---");
        bitwiseComplement();
    }

}
