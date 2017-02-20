package com.peierlong.spring.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 包名: com.peierlong.spring.aop
 * 创建人 : Elong
 * 时间: 20/02/2017 3:19 PM
 * 描述 : AOP测试类
 */
public class AopTest {

    @Test
    public void testAOP() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:helloWorld.xml");
        HelloWorldService helloWorldService = context.getBean("helloWorldService", HelloWorldService.class);
        helloWorldService.sayHello();
    }
}
