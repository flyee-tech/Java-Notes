package com.peierlong.nio;

/**
 * 包名: com.peierlong.nio
 * 创建人 : Elong
 * 时间: 2017/1/7 上午12:20
 * 描述 : TimeClient客户端
 */
public class TimeClient {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 8080;
        new Thread(new TimeClientHandle(ip, port), "NIO-TimeClient-001").start();
    }
}
