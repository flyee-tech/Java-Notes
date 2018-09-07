package com.peierlong.design.patterns.prototype;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/7
 */
public class ConcretePrototype extends Prototype {

    private String filed;

    public ConcretePrototype(String filed) {
        this.filed = filed;
    }

    @Override
    Prototype myClone() {
        return new ConcretePrototype(filed);
    }

    @Override
    public String toString() {
        return filed;
    }
}
