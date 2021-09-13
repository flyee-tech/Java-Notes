package com.peierlong.concurrency;

import java.util.LinkedList;
import java.util.List;

/**
 * 包名: com.elong.concurrency.ifeve
 * 创建人 : Elong
 * 时间: 9/23/16 4:08 PM
 * 描述 : 一个阻塞队列的简单实现
 */
public class BlockingQueue {

    private List<Object> queue = new LinkedList<Object>();
    private int limit = 10;

    public BlockingQueue(int limit) {
        this.limit = limit;
    }

    /**
     * 入队
     * @param item Object
     * @throws InterruptedException ...
     */
    public synchronized void enqueue(Object item) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            wait();
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }

    /**
     * 出队
     * @return Object
     * @throws InterruptedException ...
     */
    public synchronized Object dequeue() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        if (this.queue.size() == this.limit) {
            notifyAll();
        }
        return this.queue.remove(0);
    }

}
