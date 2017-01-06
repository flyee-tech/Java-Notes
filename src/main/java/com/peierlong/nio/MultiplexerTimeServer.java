package com.peierlong.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * 包名: com.peierlong.nio
 * 创建人 : Elong
 * 时间: 2017/1/6 下午11:35
 * 描述 : 服务端任务
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private volatile boolean stop;

    /**
     * 初始化多路复用器，绑定监听端口
     * @param port 端口
     */
    MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务器启动成功，端口号: " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void stop(){
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    //执行key对应的操作
                    try {
                        handleInput(key);
                    } catch (IOException e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                SocketChannel socketChannel = channel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                SocketChannel channel = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readByte = channel.read(readBuffer);
                if (readByte > 0) { //读到了字节，对字节进行编解码
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("服务器收到请求 : " + body);
                    String currentTime = "QueryTimeOrder".equals(body) ? new Date().toString() : "ERROR";
                    //执行异步的write
                    doWrite(channel, currentTime);
                } else if (readByte < 0) {  //链路已经关闭，需要关闭SocketChannel，释放资源
                    key.cancel();
                    channel.close();
                }
            }
        }
    }

    private void doWrite(SocketChannel socketChannel, String response) throws IOException {
        if (response != null && response.length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            socketChannel.write(writeBuffer);
        }
    }


}
