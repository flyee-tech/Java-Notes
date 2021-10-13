package com.peierlong.concurrency;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author peiel
 */
public class ThreadPoolOomTest {

    public static void main(String[] args) {
        // 以下是错误的方式，会造成 OOM
//        mistakeWay();
        // 以下是正确的方式
//        correctWay();
    }

    public static void mistakeWay() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        int i = 0;
        while (true) {
            executorService.submit(new Task(i++));
        }
    }

    public static void correctWay() {
        // 获取处理器数量和创建线程工厂
        int threadCnt = Runtime.getRuntime().availableProcessors();
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        // Common Thread Pool
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadCnt, 200, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024), factory, new ThreadPoolExecutor.AbortPolicy());
        int i = 0;
        while (true) {
            executor.execute(new Task(i++));
        }
    }

    public static class Task implements Runnable {
        private final int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " " + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
