package com.peierlong.design.patterns.adapter;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/11
 */
public class Client {

    public static void main(String[] args) {
        Turkey turkey = new WildTurkey();
        Duck duck = new TurkeyAdapter(turkey);
        duck.quack();
    }

}
