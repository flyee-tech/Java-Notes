package com.peierlong.jvm;

/**
 *
 * 局部变量表中Slot重用引起的GC问题
 *
 * @
 * @author elong
 * @version V1.0
 * @date 2018/3/28
 */
public class TestLocalVariablesGC {

    public static void main(String[] args) {
        byte[] placeholder = new byte[64 * 1024 * 1024];
        int a = 1;
        System.gc();
    }

    /*
        byte[] placeholder = new byte[64 * 1024 * 1024];
        System.gc();

        {
        byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        System.gc();

        以上两种情况placeholder都没有被回收
        output：
        [GC 69499K->66048K(251392K), 0.0019110 secs]
        [Full GC 66048K->65985K(251392K), 0.0148720 secs]

        ---------------------------------------------------

        byte[] placeholder = new byte[64 * 1024 * 1024];
        int a = 1;
        System.gc();

        定义int a 复用了 placeholder 所占用的slot，所以gc回收了局部变量表中占用的空间
        备注：针对局部变量大对象，可以显式的将其置为null来让GC回收。
        output:
        [GC 69499K->66112K(251392K), 0.0012770 secs]
        [Full GC 66112K->449K(251392K), 0.0109550 secs]

     */


}
