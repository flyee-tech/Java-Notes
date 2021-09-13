package com.peierlong.concurrency;

/**
 * 包名: com.elong.concurrency
 * 创建人 : Elong
 * 时间: 2016/12/15 下午4:40
 * 描述 : 线程安全的数值生成器
 */
public class Sequence {

    private int number;

    /**
     * 使用synchronized 使方法成为同步方法
     */
    public synchronized int getNumber() {
        return number++;
    }
}
