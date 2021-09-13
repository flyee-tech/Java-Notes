package com.peierlong.design.patterns.composite;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/11
 */
public class Left extends Component {

    public Left(String name) {
        super(name);
    }

    @Override
    void print(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("--");
        }
        System.out.println("left:" + name);
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }

}
