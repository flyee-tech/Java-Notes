package com.peierlong.jvm;

/**
 * 包名: com.peierlong.jvm
 * 创建人 : Elong
 * 时间: 06/04/2017 6:38 PM
 * 描述 : 根据运行结果，可以看到伪共享（False Sharing）对性能的影响
 */
public final class FalseSharing implements Runnable {

    public final static int NUM_THREADS = 4; // change
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;

    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        final long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start));
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    public final static class VolatileLong {
        public volatile long value = 0L;
        public long p1, p2, p3, p4, p5, p6; // comment out
    }

    /*
     运行结果:
     线程数       填充(s)         不填充(s)
     4           4.850679		44.142519
     3           4.317808		34.061760
     2           4.132944		19.784708
     1           4.371287		4.283115
     */


}
