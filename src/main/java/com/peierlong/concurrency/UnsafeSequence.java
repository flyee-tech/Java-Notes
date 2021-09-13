package com.peierlong.concurrency;

/**
 * 包名: com.elong.concurrency
 * 创建人 : Elong
 * 时间: 2016/12/15 下午4:14
 * 描述 : 非线程安全的数值产生器
 */
public class UnsafeSequence {

    private int number;

    public int getNumber() {
        return number++;
    }

}
