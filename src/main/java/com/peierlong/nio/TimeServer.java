package com.peierlong.nio;

/**
 * 包名: com.peierlong.nio
 * 创建人 : Elong
 * 时间: 2017/1/6 下午11:29
 * 描述 : TimeServer服务端
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(port);
        new Thread(multiplexerTimeServer, "NIO-MultiplexerTimeServer-001").start();
    }

}
