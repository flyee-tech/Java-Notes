package com.peierlong.nio.ifeve;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Peiel
 * @version V1.0
 * @date 2019-03-22
 */
public class FileChannelDemo {

    public static void main(String[] args) throws IOException {

        // Channel 第一种获取方式
//        RandomAccessFile fis = new RandomAccessFile("/Users/peiel/a.txt", "rw");
//        FileChannel fileChannel = fis.getChannel();

        // Channel 第二种获取方式
        FileInputStream fis = new FileInputStream("/Users/peiel/a.txt");
        FileChannel fileChannel = fis.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(4);

        int len;
        while ((len = fileChannel.read(buf)) != -1) {


//            System.out.println("len : " + len);
            buf.flip();


            // 转换成数组读取的方式
//            System.out.print(new String(buf.array(), 0, len));


            // 直接读取的方式
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }

            buf.clear();

//            System.out.println();
//            System.out.println("position : " + fileChannel.position());
        }


        fileChannel.close();
        fis.close();


    }

}
