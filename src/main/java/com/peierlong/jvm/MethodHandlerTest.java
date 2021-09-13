package com.peierlong.jvm;

import static java.lang.invoke.MethodHandles.lookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

/**
 * 包名: com.peierlong.jvm
 * 创建人 : Elong
 * 时间: 14/02/2017 3:15 PM
 * 描述 : Java对动态类型语言支持
 *      JSR-292 Method Handler 基础用法演示
 */
public class MethodHandlerTest {

    static class ClassA {
        public void println(String s){
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        getPrintlnMH(obj).invokeExact("裴二龙");
    }

    private static MethodHandle getPrintlnMH(Object reveiver) throws Throwable {
        MethodType mt = MethodType.methodType(void.class, String.class);
        return lookup().findVirtual(reveiver.getClass(), "println", mt).bindTo(reveiver);
    }

}
