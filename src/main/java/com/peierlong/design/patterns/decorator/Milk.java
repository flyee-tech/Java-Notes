package com.peierlong.design.patterns.decorator;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/12
 */
public class Milk extends BeverageDecorator {

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost() + 1;
    }

}
