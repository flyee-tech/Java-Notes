package com.peierlong.design.patterns.flyweight;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/12
 */
public class ConcreteFlyweight implements Flyweight {
    private String intrinsicState;

    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void doOperation(String extrinsicState) {
        System.out.println("obj address    : " + System.identityHashCode(this));
        System.out.println("IntrinsicState : " + intrinsicState);
        System.out.println("ExtrinsicState : " + extrinsicState);
    }

}
