package com.peierlong.concurrency;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 包名: com.elong.concurrency.ifeve
 * 创建人 : Elong
 * 时间: 9/25/16 5:36 PM
 * 描述 : java.util.concurrent.atomic 原子包boolean原子性示例
 */
public class CASDemo {

    public static void main(String[] args) {



    }


    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public boolean lock(){
        return atomicBoolean.compareAndSet(false, true);
    }

}
