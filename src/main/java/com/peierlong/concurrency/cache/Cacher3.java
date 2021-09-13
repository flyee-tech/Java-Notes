package com.peierlong.concurrency.cache;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 包名: com.elong.concurrency.custom.cache
 * 创建人 : Elong
 * 时间: 2016/12/29 下午4:01
 * 描述 : 使用FutureTask异步进行耗时运算， 缓存采用先缓存再计算的方式。
 */
public class Cacher3<A, V> implements Computable<A, V>{
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public Cacher3(Computable<A, V> computable) {
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
            cache.put(arg, future);
            ft.run();
        }
        try {
            return future.get();
        } catch (ExecutionException e) {
            throw new InterruptedException(e.getMessage());
        }

    }


}
