package com.peierlong.jvm;

/**
 * intern 字符串常量池测试代码
 *
 * @author elong
 * @version V1.0
 * @date 2018/1/31
 */
public class TestConstantPool {

    public static void main(String[] args) {
        String s1 = new String("1");

        s1.intern();

        String s2 = "1";

        System.out.println(s1 == s2);


        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";

        System.out.println(s3 == s4);


    }



}
