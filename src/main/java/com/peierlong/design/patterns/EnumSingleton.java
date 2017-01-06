package com.peierlong.design.patterns;

import java.lang.reflect.Constructor;
import java.util.concurrent.TimeUnit;

/**
 * 包名: com.elong.design.patterns
 * 创建人 : Elong
 * 时间: 2016/11/25 下午6:40
 * 描述 : 单元素的枚举类型实现单例
 * 枚举创建的单例具有天生抵抗反射创建对象
 */
public enum EnumSingleton {

    INSTANCE;

    private String objName;

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public static void main(String[] args) {
        EnumSingleton firstSingleton = EnumSingleton.INSTANCE;
        firstSingleton.setObjName("firstName");
        System.out.println(firstSingleton.getObjName());

        EnumSingleton secondSingleton = EnumSingleton.INSTANCE;
        secondSingleton.setObjName("secondName");
        System.out.println(firstSingleton.getObjName());
        System.out.println(secondSingleton.getObjName());

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Constructor<EnumSingleton> constructor = EnumSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            EnumSingleton enumSingleton = constructor.newInstance();
            System.out.println(enumSingleton.getObjName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
