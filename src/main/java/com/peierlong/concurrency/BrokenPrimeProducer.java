package com.peierlong.concurrency;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 包名: com.elong.concurrency
 * 创建人 : Elong
 * 时间: 2016/12/31 上午10:43
 * 描述 : 素数阻塞队列示例
 * 本来想造出一个中断产生的永久阻塞，结果没造出来 (￣∇￣)
 */
public class BrokenPrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> blockingQueue;
    private boolean cancelled = false;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        BigInteger bigInteger = BigInteger.ONE;
        try {
            while (!cancelled) {
                bigInteger = bigInteger.nextProbablePrime();
                System.out.println("put: " + bigInteger);
                blockingQueue.put(bigInteger);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cancell() {
        cancelled = true;
    }

    private boolean isEmpty() {
        return blockingQueue.isEmpty();
    }

    private BigInteger take() throws InterruptedException {
        return blockingQueue.take();
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<BigInteger> blockingQueue = new LinkedBlockingDeque<>(MAX_PRIORITY);
        BrokenPrimeProducer producer = new BrokenPrimeProducer(blockingQueue);
        producer.start();
        Thread.sleep(1000);
        try {
            while (!producer.isEmpty()) {
                Thread.sleep(1000);
                System.out.println("take: " + producer.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            producer.cancell();
        }

    }
}
