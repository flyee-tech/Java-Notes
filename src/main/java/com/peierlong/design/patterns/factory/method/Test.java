package com.peierlong.design.patterns.factory.method;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/6
 */
public class Test {

    public static void main(String[] args) {
        Factory fa = new FactoryA();
        Product pa = fa.Manufacture();
        pa.show();

        Factory fb = new FactoryB();
        Product pb = fb.Manufacture();
        pb.show();

    }

}
