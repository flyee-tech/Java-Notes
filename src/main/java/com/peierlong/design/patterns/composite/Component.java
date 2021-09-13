package com.peierlong.design.patterns.composite;

/**
 * 设计模式之组合
 *
 * @author elong
 * @version V1.0
 * @date 2018/9/11
 */
public abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public void print() {
        print(0);
    }

    abstract void print(int level);

    abstract public void add(Component component);

    abstract public void remove(Component component);
}
