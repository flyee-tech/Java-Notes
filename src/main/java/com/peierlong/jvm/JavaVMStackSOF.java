package com.peierlong.jvm;

/**
 * 包名: com.peierlong.jvm
 * 创建人 : Elong
 * 时间: 12/04/2017 4:30 PM
 * 描述 : 虚拟机栈和本地方法栈OOM/SOF测试
 * JVM参数 : -Xss160k
 */
public class JavaVMStackSOF {
    private int stackLength = 0;

    private void stackLeak() {
        stackLength ++;
        stackLeak();
    }


    public static void main(String[] args) {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();

        try {
            javaVMStackSOF.stackLeak();
        } catch (Exception e) {
            System.out.println(javaVMStackSOF.stackLength);
            e.printStackTrace();
        }

    }


}
