package com.peierlong.jvm;

/**
 *
 * 常量在编译阶段会存入调用类的常量池中，本质上并没有引用定义常量的类，因此不会触发定义常量的类的初始化
 *
 * @author elong
 * @version V1.0
 * @date 2018/5/25
 */
public class ConstClass {
    static {
        System.out.println("ConstClass init");
    }
    public final static String HELLOWORLD = "hello world";
}

