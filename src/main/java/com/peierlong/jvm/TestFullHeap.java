package com.peierlong.jvm;

import java.util.ArrayList;

/**
 * 包名: com.peierlong.jvm
 * 创建人 : Elong
 * 时间: 08/02/2017 11:19 AM
 * 描述 : 使用jConsole可视化工具查看虚拟机内存状态
 */
public class TestFullHeap {
    static class OOMObject {
        public byte[] bytes = new byte[64 * 1024];
    }

    private static void fullHeap(int number) throws InterruptedException {
        ArrayList<OOMObject> oomObjects = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Thread.sleep(1000);
            oomObjects.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fullHeap(1000);
    }
}
