package com.peierlong.concurrency;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 包名: com.elong.concurrency.ifeve
 * 创建人 : Elong
 * 时间: 16/9/19 下午6:11
 * 描述 : ThreadLocalTest
 */
public class ThreadLocalTest {


    public static void main(String[] args) throws Exception{
        MyRunnable myRunnable = new MyRunnable();
        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);

        t1.start();
        t2.start();

        t1.join();  //Waits for this thread to die.  主线程阻塞,等待t1线程执行完毕。
        System.out.println(new SimpleDateFormat("mm:ss:SSS").format(new Date()) + "   finish t1.join()");
        t2.join();
        System.out.println(new SimpleDateFormat("mm:ss:SSS").format(new Date()) + "   finish t2.join()");

    }


    private static class MyRunnable implements Runnable {
//        private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
        private List<Integer> list =new ArrayList<Integer>();
        public void run() {
            synchronized (this){
                list.add(0, (int) (Math.random() * 100D));
//                threadLocal.set((int) (Math.random() * 100D));
            }


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " : " + list.get(0));

        }
    }

}
