package com.peierlong.lambda;

/**
 *
 * 使用Lambda表达式创建线程
 *
 * @author elong
 * @version V1.0
 * @date 2018/7/20
 */
public class CreateThreadDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("Hello World"));
        thread.start();
    }


}
