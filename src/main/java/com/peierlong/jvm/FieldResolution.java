package com.peierlong.jvm;

/**
 * 类加载过程中 > 解析阶段 > 字段解析
 * 如果诸事点Sub中的A变量，编译器会提示 The field Sub.A is ambiguous
 *
 * @author elong
 * @version V1.0
 * @date 2018/5/25
 */
public class FieldResolution {
    interface Interface0 {
        int A = 0;
    }

    interface Interface1 extends Interface0 {
        int A = 1;
    }

    interface Interface2 {
        int A = 2;
    }

    static class Parent implements Interface1 {
        public static int A = 3;
    }

    static class Sub extends Parent implements Interface2 {
        public static int A = 4;
    }

    public static void main(String[] args) {
        System.out.println(Sub.A);
    }

}
