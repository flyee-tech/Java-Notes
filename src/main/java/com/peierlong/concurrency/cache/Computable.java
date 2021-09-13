package com.peierlong.concurrency.cache;

/**
 * 包名: com.elong.concurrency.custom.cache
 * 创建人 : Elong
 * 时间: 2016/12/29 下午3:57
 * 描述 : 缓存接口
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
