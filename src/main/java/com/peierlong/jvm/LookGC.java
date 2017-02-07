package com.peierlong.jvm;

/**
 * 包名: com.peierlong.jvm
 * 创建人 : Elong
 * 时间: 07/02/2017 2:18 PM
 * 描述 : 观察GC日志，当Eden空间不足，且无法放入Survivor空间时，通过担保机制提前转移到老年代
 * JVM参数: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 */
public class LookGC {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {

        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];

        /*输出
        [GC[DefNew: 7307K->332K(9216K), 0.0072810 secs] 7307K->6476K(19456K), 0.0073280 secs] [Times: user=0.00 sys=0.01, real=0.01 secs]
        Heap
        def new generation   total 9216K, used 4678K [0x00000007f9a00000, 0x00000007fa400000, 0x00000007fa400000)
        eden space 8192K,  53% used [0x00000007f9a00000, 0x00000007f9e3e6e0, 0x00000007fa200000)
        from space 1024K,  32% used [0x00000007fa300000, 0x00000007fa353248, 0x00000007fa400000)
        to   space 1024K,   0% used [0x00000007fa200000, 0x00000007fa200000, 0x00000007fa300000)
        tenured generation   total 10240K, used 6144K [0x00000007fa400000, 0x00000007fae00000, 0x00000007fae00000)
        the space 10240K,  60% used [0x00000007fa400000, 0x00000007faa00030, 0x00000007faa00200, 0x00000007fae00000)
        compacting perm gen  total 21248K, used 2920K [0x00000007fae00000, 0x00000007fc2c0000, 0x0000000800000000)
        the space 21248K,  13% used [0x00000007fae00000, 0x00000007fb0da110, 0x00000007fb0da200, 0x00000007fc2c0000)
        No shared spaces configured.
        */
    }

}
