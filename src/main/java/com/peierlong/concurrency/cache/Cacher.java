package com.peierlong.concurrency.cache;

import java.util.concurrent.*;

/**
 * 包名: com.elong.concurrency.custom.cache
 * 创建人 : Elong
 * 时间: 2016/12/29 下午4:01
 * 描述 : 使用ConcurrentHashMap的putIfAbsent原子的进行缓存，来解决重复计算的问题。
 */
public class Cacher<A, V> implements Computable<A, V>{
    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public Cacher(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        Future<V> future = cache.get(arg);
        if (future == null) {
            Callable<V> callable = new Callable<V>() {
                @Override
                public V call() throws InterruptedException {
                    return computable.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<>(callable);
            future = ft;
            cache.putIfAbsent(arg, future);
            ft.run();
        }
        try {
            return future.get();
        } catch (ExecutionException e) {
            throw new InterruptedException(e.getMessage());
        }

    }


}
