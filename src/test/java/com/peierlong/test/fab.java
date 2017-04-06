package com.peierlong.test;

/**
 * 包名: com.peierlong.test
 * 创建人 : Elong
 * 时间: 06/04/2017 10:04 PM
 * 描述 : 斐波那契数列实现
 */
public class fab {

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            System.out.print(i + " ");
            System.out.print(fab(i) + " |");
        }

    }


    private static long fab(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1 || i == 2) {
            return 1;
        }
        return fab(i - 1) + fab(i - 2);
    }

}
