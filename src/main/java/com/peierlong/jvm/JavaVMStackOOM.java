package com.peierlong.jvm;

/**
 * 虚拟机栈和本地方法栈OOM测试
 *
 * @author elong
 * @version V1.0
 * @date 2018/1/31
 * @VMArgs -Xss2M
 */
public class JavaVMStackOOM {

    public static void main(String[] args) {
        JavaVMStackOOM javaVMStackOOM = new JavaVMStackOOM();
        javaVMStackOOM.stackLeakByThread();
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dntStop();
                }
            });
            thread.start();
        }
    }

    private void dntStop() {
        while (true) {
        }
    }

}
