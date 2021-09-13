package com.peierlong.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Peiel
 * @version V1.0
 * @date 2020/7/22
 */
public class CopyFileDemo {

    /**
     * 实现一个文件复制功能，通过IO操作的字节流
     *
     * @param src  源文件
     * @param desc 目标文件
     */
    public static void copyFile(String src, String desc) throws IOException {
        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(desc);
        byte[] buffer = new byte[20 * 1024];    //20kb buffer
        int cnt;
        while ((cnt = in.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, cnt);
        }
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        copyFile("/Users/peiel/Java-Notes/src/main/java/com/peierlong/io/CopyFileDemo.java", "/Users/peiel/Java-Notes/src/main/java/com/peierlong/io/CopyFileDemo.java.bak");
    }

}
