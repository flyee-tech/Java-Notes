package com.peierlong.best.practices;

import java.util.Arrays;
import java.util.Set;

/**
 * 包名: com.elong.effective
 * 创建人 : Elong
 * 时间: 2016/12/7 下午5:43
 * 描述 : 使用反射类获取对象的实例
 */
public class ReflectiveTest {

    public static void main(String[] args) {
        Class<?> cl = null;
        try {
            cl = Class.forName("java.util.TreeSet");
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found!");
            System.exit(1);
        }

        Set<String> s = null;

        try {
            s = (Set<String>) cl.newInstance();
        } catch (InstantiationException e) {
            System.err.println("Class not instantiation!");
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.err.println("Class not illegalAccess!");
            System.exit(1);
        }

        if (s != null) {
            s.addAll(Arrays.asList("a", "b", "a"));
        }

        System.out.println(s);

    }


}
