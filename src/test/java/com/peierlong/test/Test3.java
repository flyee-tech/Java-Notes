package com.peierlong.test;

/**
 * @author elong
 * @version V1.0
 * @date 2018/4/3
 */
public class Test3 {


    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {

    }

    public void sayHello(Human human) {
        System.out.println("hello human");
    }

    public void sayHello(Man man) {
        System.out.println("hello man");
    }

    public void sayHello(Woman woman) {
        System.out.println("hello woman");
    }


    public static void main(String[] args) {

        Human man = new Man();
        Human woman = new Woman();

        Test3 test3 = new Test3();
        test3.sayHello((Man) man);
        test3.sayHello((Woman) woman);
    }
}
