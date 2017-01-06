package com.peierlong.concurrency.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 包名: com.elong.concurrency.custom.cache
 * 创建人 : Elong
 * 时间: 2016/12/29 下午4:01
 * 描述 : 第一步尝试，使用HashMap来存储数据，由于HashMap不是线程安全的，需要在compute方法上加锁，来保证每次只有一个线程访问缓存。
 * 存在问题 : 性能低，容易阻塞大量线程。
 */
public class Cacher1<A, V> implements Computable<A, V>{
    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> computable;

    public Cacher1(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V value = cache.get(arg);
        if (value == null) {
            value = computable.compute(arg);
            cache.put(arg, value);
        }
        return value;
    }
}
