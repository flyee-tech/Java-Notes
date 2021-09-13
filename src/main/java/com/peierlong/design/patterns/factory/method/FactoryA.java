package com.peierlong.design.patterns.factory.method;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/6
 */
public class FactoryA extends Factory {
    @Override
    public Product Manufacture() {
        return new ProductA();
    }
}
