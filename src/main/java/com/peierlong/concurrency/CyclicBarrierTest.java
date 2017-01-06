package com.peierlong.concurrency;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 包名: com.elong.concurrency
 * 创建人 : Elong
 * 时间: 2016/12/28 下午8:01
 * 描述 : 同步工具类: CyclicBarrier
 * CyclicBarrier 会等待所有的线程到达触发点执行barrier中的线程
 */
public class CyclicBarrierTest {
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CyclicBarrierTest() {
        int count = Runtime.getRuntime().availableProcessors();
        System.out.println("cpu 数量: " + count);
        barrier = new CyclicBarrier(count, new Runnable() {
            @Override
            public void run() {
                System.out.println("======= 做一些事情 =======");
            }
        });
        workers = new Worker[count];
        for (int i = 0; i < count; i++)
            workers[i] = new Worker();
    }

    class Worker implements Runnable {
        @Override
        public void run() {

            Random random = new Random();
            int i = random.nextInt(100);
            System.out.println(Thread.currentThread().getName() + " " + i*100);
            try {
                Thread.sleep(i * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private void start(){
        for (Worker worker : workers) {
            new Thread(worker).start();
        }
    }

    public static void main(String[] args) {
        CyclicBarrierTest barrierTest = new CyclicBarrierTest();
        barrierTest.start();
    }
}

