package com.peierlong.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 包名: com.elong.concurrency
 * 创建人 : Elong
 * 时间: 2016/12/24 下午7:09
 * 描述 : 闭锁: 使用CountDownLatch类启动和停止线程
 */
public class TestHarness {

    public static long timeTask(int threadNum, final Runnable runnable) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                try {
                    startGate.await();
                    System.out.println(Thread.currentThread().getName() + "开始执行任务 ...");
                    try {
                        runnable.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        long startTime = System.currentTimeMillis();
        startGate.countDown();
        System.out.println(endGate.getCount());
        endGate.await();
        System.out.println(endGate.getCount());
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

//***************** test ***************************************************************
    public static void main(String[] args) throws InterruptedException {
//        Runnable runnable = () -> {
//            try {
//                TimeUnit.MILLISECONDS.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + " 任务处理完成 ....");
//        };
//        long runningTime = timeTask(6, runnable);
//        System.out.println("最终执行耗时: " + runningTime);

        countDownLatchDemo();
    }
//***************** test ***************************************************************

//***************** new demo ***************************************************************
    public static void countDownLatchDemo() throws InterruptedException {
        final int totalThread = 10;
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            executorService.execute(() -> {
                System.out.println("run ... ");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("end ...");
        executorService.shutdown();
    }
//***************** new demo ***************************************************************


}
