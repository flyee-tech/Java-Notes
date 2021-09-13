package com.peierlong.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * 包名: com.peierlong.jvm
 * 创建人 : Elong
 * 时间: 14/02/2017 3:39 PM
 * 描述 : 如何在子类方法中调用祖类方法(有问题)
 */
public class MethodHandlerTest2 {

    class GrandFather {
        void thinking() {
            System.out.println("im GrandFather");
        }
    }

    class Father extends GrandFather {
        void thinking() {
            System.out.println("im Father");
        }
    }

    class Son extends Father {

        void thinking() {
            try {
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle methodHandle = lookup().findSpecial(GrandFather.class, "thinking", mt, Son.class);
                methodHandle.invoke(this);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Throwable {
        (new MethodHandlerTest2().new Son()).thinking();
    }


}
