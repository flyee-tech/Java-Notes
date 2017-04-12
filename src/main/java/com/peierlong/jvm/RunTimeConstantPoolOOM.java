package com.peierlong.jvm;

/**
 * 包名: com.peierlong.jvm
 * 创建人 : Elong
 * 时间: 12/04/2017 6:31 PM
 * 描述 : 运行时常量池小知识
 */
public class RunTimeConstantPoolOOM {
    public static void main(String[] args) {

        //jdk1.7第一次加载的字符串 在遇到intern方法时，不会实现复制，只是在常量池中记录首次出现实例引用
        //jdk1.6第一次加载字符串时，会把当前实例复制到永久代（方法区）>运行时常量池中，返回的时常量池中的引用
        String str = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str.intern() == str);

        //由于"java"字符串已经在常量池中了，intern返回常量池中的引用，所以返回false
        String str1 = new StringBuilder("ja").append("va").toString();
        System.out.println(str1.intern() == str1);

    }
}
