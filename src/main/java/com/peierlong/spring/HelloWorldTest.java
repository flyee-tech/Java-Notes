package com.peierlong.spring;

import com.peierlong.spring.aop.HelloWorldService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 包名: com.peierlong.spring
 * 创建人 : Elong
 * 时间: 10/04/2017 10:56 AM
 * 描述 : 面向接口编程 根据ApplicationContext来拿到一个Bean
 */
public class HelloWorldTest {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:helloWorld.xml");
        HelloWorldService helloWorldService = applicationContext.getBean("helloWorldService", HelloWorldService.class);
        helloWorldService.sayHello();

    }


}
