package com.peierlong.jvm;

import java.util.ArrayList;

/**
 * 包名: com.peierlong.jvm
 * 创建人 : Elong
 * 时间: 12/04/2017 2:08 PM
 * 描述 : Java堆内存溢出异常测试
 * JVM参数 : -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {

    public static void main(String[] args) {
        ArrayList<OOMObject> oomObjects = new ArrayList<>();
        for (;;) {
            oomObjects.add(new OOMObject());
        }
    }

    static class OOMObject {
    }

}
