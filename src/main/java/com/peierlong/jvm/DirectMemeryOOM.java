package com.peierlong.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 本机直接内存溢出
 * @author elong
 * @version V1.0
 * @date 2018/1/31
 * @VMArgs -Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectMemeryOOM {
    private static final int _100MB = 1024 * 1024 * 100;

    public static void main(String[] args) throws IllegalAccessException {
        DirectMemeryOOM s = new DirectMemeryOOM();
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while (true) {
            unsafe.allocateMemory(_100MB);
        }
    }

}

