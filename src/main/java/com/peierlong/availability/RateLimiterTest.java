package com.peierlong.availability;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * 用于应用层限流,平滑一个接口的请求速率
 *
 * 令牌通算法限流例子
 *
 * @author peierlong
 * @version V1.0
 * @date 2020/11/21
 */
public class RateLimiterTest {

    public static void test1() {
        // 每秒新增5个令牌，每隔200ms 新增一个令牌。
        RateLimiter rateLimiter = RateLimiter.create(5);
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
    }

    public static void test2() {
        // 每秒新增5个令牌，每隔200ms 新增一个令牌。
        RateLimiter rateLimiter = RateLimiter.create(5);
        System.out.println(rateLimiter.acquire(5));
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
    }

    public static void test3() {
        // 每秒新增5个令牌，每隔200ms 新增一个令牌。
        RateLimiter rateLimiter = RateLimiter.create(5);
        System.out.println(rateLimiter.acquire(10));
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
    }

    public static void test4() throws InterruptedException {
        //平滑速率的限流工具
        RateLimiter rateLimiter = RateLimiter.create(5, 1000, TimeUnit.MILLISECONDS);
        for (int i = 0; i < 5; i++) {
            System.out.println(rateLimiter.acquire());
        }
        Thread.sleep(1000L);
        for (int i = 0; i < 5; i++) {
            System.out.println(rateLimiter.acquire());
        }
    }


    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();
        test4();
    }
}
