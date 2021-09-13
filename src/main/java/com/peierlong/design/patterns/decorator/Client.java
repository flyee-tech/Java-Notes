package com.peierlong.design.patterns.decorator;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/12
 */
public class Client {

    public static void main(String[] args) {
        Beverage beverage = new HouseBlend();
        beverage = new Mocha(beverage);
        beverage = new Milk(beverage);

        System.out.println(beverage.cost());
    }

}
