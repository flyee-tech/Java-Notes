package com.peierlong.design.patterns.adapter;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/11
 */
public class TurkeyAdapter implements Duck {
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

}
