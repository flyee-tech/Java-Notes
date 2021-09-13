package com.peierlong.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author elong
 * @version V1.0
 * @date 2018/8/23
 */
public class NioUtil {

    public static void fastCopy(String src, String dist) throws IOException {
        FileInputStream fin = new FileInputStream(src);
        FileChannel fcin = fin.getChannel();

        FileOutputStream fout = new FileOutputStream(dist);
        FileChannel fcout = fout.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true) {
            int r = fcin.read(buffer);
            if (r == -1) {
                break;
            }
            buffer.flip();
            fcout.write(buffer);
            buffer.clear();
        }

    }

    public static void main(String[] args) {
        try {
            fastCopy("/Users/elong/Desktop/a.txt", "/Users/elong/Desktop/b.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
