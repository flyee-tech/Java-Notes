package com.peierlong.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 包名: com.elong.concurrency.ifeve
 * 创建人 : Elong
 * 时间: 16/9/19 下午3:27
 * 描述 : 测试notify和wait的执行流程
 */
public class MyWaitNotify {

    MonitorObject myMonitorObject = new MonitorObject();

    public void doWait(){
        synchronized (myMonitorObject){
            System.out.println(Thread.currentThread().getName() + "1");
            try {
                System.out.println(Thread.currentThread().getName() + "2");
                myMonitorObject.wait();
                System.out.println(Thread.currentThread().getName() + "3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void doNotify(){
        System.out.println(Thread.currentThread().getName() + "4");
        synchronized (myMonitorObject){
            System.out.println(Thread.currentThread().getName() + "5");
            myMonitorObject.notify();
            System.out.println(Thread.currentThread().getName() + "6");

        }
    }

    public static void main(String[] args) {
        final MyWaitNotify myWaitNotity = new MyWaitNotify();
        Thread one = new Thread("one"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run()");
                myWaitNotity.doWait();
            }
        };
        one.start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.State state = one.getState();
        System.out.println(state);

        Thread two = new Thread("two"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run()");
                myWaitNotity.doNotify();
            }
        };
        two.start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(two.getState());


    }

}


class MonitorObject{}