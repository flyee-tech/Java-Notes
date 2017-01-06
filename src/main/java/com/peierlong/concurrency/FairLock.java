package com.peierlong.concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名: com.elong.concurrency.ifeve
 * 创建人 : Elong
 * 时间: 16/9/20 下午5:40
 * 描述 : 公平锁的实现
 */
public class FairLock {
    private boolean           isLocked       = false;
    private Thread            lockingThread  = null;
    private List<QueueObject> waitingThreads =
            new ArrayList<QueueObject>();

        public void lock() throws InterruptedException{
            QueueObject queueObject           = new QueueObject();
            boolean     isLockedForThisThread = true;
            synchronized(this){
                waitingThreads.add(queueObject);
            }

            while(isLockedForThisThread){
                synchronized(this){
                    System.out.println(Thread.currentThread().getName() + " " + (waitingThreads.get(0) != queueObject));
                    isLockedForThisThread =
                            isLocked || waitingThreads.get(0) != queueObject;
                    if(!isLockedForThisThread){
                        System.out.println("当前执行线程 " + Thread.currentThread().getName());
                        isLocked = true;
                        waitingThreads.remove(queueObject);
                        lockingThread = Thread.currentThread();
                        return;
                    }
                }
            try{
                Thread.sleep(8000);
                System.out.println("doWait()前 : " + Thread.currentThread().getName());
                queueObject.doWait();
                System.out.println("doWait()后 : " + Thread.currentThread().getName());
            }catch(InterruptedException e){
                synchronized(this) { waitingThreads.remove(queueObject); }
                throw e;
            }
        }
    }

    public synchronized void unlock(){
        if(this.lockingThread != Thread.currentThread()){
            throw new IllegalMonitorStateException(
                    "Calling thread has not locked this lock");
        }
        isLocked      = false;
        lockingThread = null;
        if(waitingThreads.size() > 0){
            waitingThreads.get(0).doNotify();
        }
    }
}