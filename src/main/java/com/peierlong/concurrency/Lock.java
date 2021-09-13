package com.peierlong.concurrency;

/**
 * 包名: com.elong.concurrency.ifeve
 * 创建人 : Elong
 * 时间: 16/9/20 下午4:54
 * 描述 : 锁的实现
 */
public class Lock {

    private boolean isLocked = false;

    private Thread lockingThread = null;

    public synchronized void lock() throws InterruptedException {
        System.out.println("进入lock方法的线程 " + Thread.currentThread().getName());

        while (isLocked) {
            System.out.println("进入isLock判断中 " + Thread.currentThread().getName() + " Get current lock Object is : " + lockingThread.getName());
            wait();

        }

        isLocked = true;

        lockingThread = Thread.currentThread();

    }

    public synchronized void unlock() {

        if (this.lockingThread != Thread.currentThread()) {

            throw new IllegalMonitorStateException(

                    "Calling thread has not locked this lock");

        }

        isLocked = false;

        lockingThread = null;

        notify();

    }
}