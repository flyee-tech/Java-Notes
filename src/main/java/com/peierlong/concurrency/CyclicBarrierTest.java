package com.peierlong.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 包名: com.elong.concurrency
 * 创建人 : Elong
 * 时间: 2016/12/28 下午8:01
 * 描述 : 同步工具类: CyclicBarrier
 * CyclicBarrier 会等待所有的线程到达触发点执行barrier中的线程
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        final int totalThread = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            executorService.execute(() -> {
                System.out.println("before");
                try {
                    // 这里是重点，等待所有线程执行完成后才向下执行。 | ★★★★★
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("after");
            });
        }
        executorService.shutdown();
    }

}

