package com.peierlong.spring.aop;

/**
 * 包名: com.peierlong.spring.aop
 * 创建人 : Elong
 * 时间: 20/02/2017 3:03 PM
 * 描述 : 定义目标接口实现类
 */
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public void sayHello() {
        System.out.println("Hello AOP!");
    }
}
