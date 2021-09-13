package com.peierlong.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 包名: com.peierlong.test
 * 创建人 : Elong
 * 时间: 27/03/2017 7:55 PM
 * 描述 :
 */
public class Test {
    @org.junit.Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        System.out.println(list);

        Collections.shuffle(list);

        System.out.println(list);



    }
}


class TestFinal {
    private final A a = new A();

    public TestFinal() {
        System.out.println("TestFinal() init");
    }
}

class A {
    public A() {
        System.out.println("A() init");
    }
}
