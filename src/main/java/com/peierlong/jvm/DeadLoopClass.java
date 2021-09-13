package com.peierlong.jvm;

/**
 * 包名: com.peierlong.jvm
 * 创建人 : Elong
 * 时间: 13/02/2017 11:37 AM
 * 描述 : 如果一个类在<clinit>()方法中耗时很长，则可能造成多个阻塞
 *  如果一个类中没有静态语句块，也没有对变量的赋值操作，那么编译器可以不为这个类生成<clinit>()方法
 */
public class DeadLoopClass {
    static {
        if (true) {
            System.out.println("当前线程 " + Thread.currentThread() + " init DeadLoopClass");
            while (true) ;
        }
    }

    public static void main(String[] args) {

        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "开始执行");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread() + "执行结束");
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();

    }
}
