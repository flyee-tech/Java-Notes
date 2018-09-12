package com.peierlong.design.patterns.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/12
 */
public class FlyweightFactory {
    private Map<String, Flyweight> flyweights = new HashMap<>();

    Flyweight getFlyweight(String intrinsicState) {
        if (!flyweights.containsKey(intrinsicState)) {
            Flyweight flyweight = new ConcreteFlyweight(intrinsicState);
            flyweights.put(intrinsicState, flyweight);
        }
        return flyweights.get(intrinsicState);
    }

}
