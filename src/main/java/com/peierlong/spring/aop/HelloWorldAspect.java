package com.peierlong.spring.aop;

/**
 * 包名: com.peierlong.spring.aop
 * 创建人 : Elong
 * 时间: 20/02/2017 3:06 PM
 * 描述 : 定义切面支持类
 */
public class HelloWorldAspect {

    public void beforeAdvice() {
        System.out.println("-- before advice --");
    }

    public void afterFinallyAdvice() {
        System.out.println("-- after finally advice --");
    }

}
