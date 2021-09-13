package com.peierlong.design.patterns.prototype;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/7
 */
public class Client {

    public static void main(String[] args) {

        Prototype p1 = new ConcretePrototype("abc");
        Prototype p2 = p1.myClone();

        System.out.println(p2);


    }

}
