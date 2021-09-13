package com.peierlong.jvm;

/**
 * 包名: com.peierlong.jvm
 * 创建人 : Elong
 * 时间: 07/02/2017 3:10 PM
 * 描述 : 使大对象直接进入老年代
 * JVM参数 : -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 *          -XX:PretenureSizeThreshold=3145728
 */
public class TestPretenureSizeThreshold {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[4 * _1MB];
        allocation2 = new byte[4 * _1MB];

        /*输出
        Heap
         def new generation   total 9216K, used 1327K [0x00000007f9a00000, 0x00000007fa400000, 0x00000007fa400000)
          eden space 8192K,  16% used [0x00000007f9a00000, 0x00000007f9b4be90, 0x00000007fa200000)
          from space 1024K,   0% used [0x00000007fa200000, 0x00000007fa200000, 0x00000007fa300000)
          to   space 1024K,   0% used [0x00000007fa300000, 0x00000007fa300000, 0x00000007fa400000)
         tenured generation   total 10240K, used 8192K [0x00000007fa400000, 0x00000007fae00000, 0x00000007fae00000)
           the space 10240K,  80% used [0x00000007fa400000, 0x00000007fac00020, 0x00000007fac00200, 0x00000007fae00000)
         compacting perm gen  total 21248K, used 2957K [0x00000007fae00000, 0x00000007fc2c0000, 0x0000000800000000)
           the space 21248K,  13% used [0x00000007fae00000, 0x00000007fb0e3588, 0x00000007fb0e3600, 0x00000007fc2c0000)
        No shared spaces configured.
         */
    }
}
