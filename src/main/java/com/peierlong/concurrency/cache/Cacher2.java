package com.peierlong.concurrency.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 包名: com.elong.concurrency.custom.cache
 * 创建人 : Elong
 * 时间: 2016/12/29 下午4:01
 * 描述 : 使用JDK提供的HashMap的同步实现类ConcurrentHashMap来提升缓存的并发性。
 */
public class Cacher2<A, V> implements Computable<A, V>{
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public Cacher2(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V value = cache.get(arg);
        if (value == null) {
            value = computable.compute(arg);
            cache.put(arg, value);
        }
        return value;
    }
}
