package com.peierlong.design.patterns.adapter;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/11
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("gobble");
    }
}
