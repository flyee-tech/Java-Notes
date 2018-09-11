package com.peierlong.design.patterns.composite;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/11
 */
public class Client {
    public static void main(String[] args) {
        Component root = new Composite("root");
        Left node1 = new Left("1");
        Composite node2 = new Composite("2");
        Left node3 = new Left("3");

        root.add(node1);
        root.add(node2);
        root.add(node3);

        Left node21 = new Left("21");
        Composite node22 = new Composite("22");
        node2.add(node21);
        node2.add(node22);

        Composite node221 = new Composite("221");
        node22.add(node221);

        root.print();

    }
}
