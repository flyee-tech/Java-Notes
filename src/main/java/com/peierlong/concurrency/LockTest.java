package com.peierlong.concurrency;

/**
 * 包名: com.elong.concurrency.ifeve
 * 创建人 : Elong
 * 时间: 16/9/20 下午4:33
 * 描述 : LockTest
 */
public class LockTest {
    public static void main(String[] args) {
        final Synchronizer synchronizer = new Synchronizer();


        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    synchronizer.doSynchronized();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    synchronizer.doSynchronized();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t2.start();


    }
}


class Synchronizer {
    FairLock lock = new FairLock();

    public void doSynchronized() throws InterruptedException {
        this.lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is run!");
            Thread.sleep(3000);
            System.out.println("-----------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.lock.unlock();
    }
}