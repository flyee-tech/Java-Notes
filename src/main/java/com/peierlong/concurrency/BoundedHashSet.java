package com.peierlong.concurrency;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 包名: com.elong.concurrency
 * 创建人 : Elong
 * 时间: 2016/12/28 下午7:11
 * 描述 : 使用Semaphore为容器设置边界
 */
public class BoundedHashSet<T> {

    public final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        semaphore = new Semaphore(bound);
    }

    public boolean add(T t) throws InterruptedException {
        semaphore.acquire();    //如果信号量不足，则阻塞。
        boolean wasAdded = false;
        try {
            wasAdded = set.add(t);
            return wasAdded;
        } finally {
            if (!wasAdded) semaphore.release();
        }
    }

    public boolean remove(T t) {
        boolean isRemove = set.remove(t);
        if (isRemove) semaphore.release();
        return isRemove;
    }

}
