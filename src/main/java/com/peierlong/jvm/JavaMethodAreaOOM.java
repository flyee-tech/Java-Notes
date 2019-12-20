package com.peierlong.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 包名: com.peierlong.jvm
 * 创建人 : Elong
 * 时间: 12/04/2017 6:51 PM
 * 描述 : 方法区内存溢出示例
 * JVM参数 : -XX:PermSize=10M -XX:MaxPermSize=10M
 * <p>
 * Java8 移除了永久代（method area），添加了元空间
 * 在Java8下添加如下配置来产生异常：
 * <p>
 * -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 * <p>
 * [out]: java.lang.OutOfMemoryError: Metaspace
 */
public class JavaMethodAreaOOM {

    public static void main(String[] args) {

        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invoke(o, objects));
            enhancer.create();
        }
    }

    private static class OOMObject {
    }


}
