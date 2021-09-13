package com.peierlong.jvm;

/**
 * GC演示，排除Java虚拟机使用引用计数算法来判断对象是否死亡
 * JVM参数: -verbose:gc -XX:+PrintGCDetails
 */
public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024 * 1024;
    private byte[] bytes = new byte[_1MB * 2];

    private static void testGC(){
        ReferenceCountingGC objectA = new ReferenceCountingGC();
        ReferenceCountingGC objectB = new ReferenceCountingGC();
        objectA.instance = objectB;
        objectB.instance = objectA;
        objectA = null;
        objectB = null;
        System.gc();
    }

    public static void main(String[] args) {
        ReferenceCountingGC.testGC();
    }

}
