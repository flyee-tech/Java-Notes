package com.peierlong.jvm;

/**
 * 包名: com.peierlong.jvm
 * 创建人 : Elong
 * 时间: 07/02/2017 3:25 PM
 * 描述 : 长期存活的对象进入老年代
 * JVM参数 : -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
            -XX:MaxTenuringThreshold=1
   什么时候进入老年代取决于MaxTenuringThreshold设置
 */
public class TestTenuringThreshold {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 10];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }
    /* -XX:MaxTenuringThreshold=1
    [GC[DefNew
    Desired survivor size 524288 bytes, new threshold 1 (max 1)
    - age   1:     439744 bytes,     439744 total
    : 5259K->429K(9216K), 0.0063100 secs] 5259K->4525K(19456K), 0.0063490 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
    [GC[DefNew
    Desired survivor size 524288 bytes, new threshold 1 (max 1)
    - age   1:       1208 bytes,       1208 total
    : 4694K->1K(9216K), 0.0020630 secs] 8790K->4512K(19456K), 0.0020950 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    Heap
     def new generation   total 9216K, used 4235K [0x00000007f9a00000, 0x00000007fa400000, 0x00000007fa400000)
      eden space 8192K,  51% used [0x00000007f9a00000, 0x00000007f9e22860, 0x00000007fa200000)
      from space 1024K,   0% used [0x00000007fa200000, 0x00000007fa2004b8, 0x00000007fa300000)
      to   space 1024K,   0% used [0x00000007fa300000, 0x00000007fa300000, 0x00000007fa400000)
     tenured generation   total 10240K, used 4511K [0x00000007fa400000, 0x00000007fae00000, 0x00000007fae00000)
       the space 10240K,  44% used [0x00000007fa400000, 0x00000007fa867da0, 0x00000007fa867e00, 0x00000007fae00000)
     compacting perm gen  total 21248K, used 2908K [0x00000007fae00000, 0x00000007fc2c0000, 0x0000000800000000)
       the space 21248K,  13% used [0x00000007fae00000, 0x00000007fb0d7110, 0x00000007fb0d7200, 0x00000007fc2c0000)
    No shared spaces configured.
     */

    /* -XX:MaxTenuringThreshold=15
    [GC[DefNew
    Desired survivor size 524288 bytes, new threshold 15 (max 15)
    - age   1:     440584 bytes,     440584 total
    : 5259K->430K(9216K), 0.0061550 secs] 5259K->4526K(19456K), 0.0061870 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
    [GC[DefNew
    Desired survivor size 524288 bytes, new threshold 15 (max 15)
    - age   1:       1168 bytes,       1168 total
    - age   2:     426160 bytes,     427328 total
    : 4695K->417K(9216K), 0.0013400 secs] 8791K->4513K(19456K), 0.0013530 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    Heap
     def new generation   total 9216K, used 4707K [0x00000007f9a00000, 0x00000007fa400000, 0x00000007fa400000)
      eden space 8192K,  52% used [0x00000007f9a00000, 0x00000007f9e308e0, 0x00000007fa200000)
      from space 1024K,  40% used [0x00000007fa200000, 0x00000007fa268540, 0x00000007fa300000)
      to   space 1024K,   0% used [0x00000007fa300000, 0x00000007fa300000, 0x00000007fa400000)
     tenured generation   total 10240K, used 4096K [0x00000007fa400000, 0x00000007fae00000, 0x00000007fae00000)
       the space 10240K,  40% used [0x00000007fa400000, 0x00000007fa800010, 0x00000007fa800200, 0x00000007fae00000)
     compacting perm gen  total 21248K, used 2920K [0x00000007fae00000, 0x00000007fc2c0000, 0x0000000800000000)
       the space 21248K,  13% used [0x00000007fae00000, 0x00000007fb0da100, 0x00000007fb0da200, 0x00000007fc2c0000)
    No shared spaces configured.
     */

}

