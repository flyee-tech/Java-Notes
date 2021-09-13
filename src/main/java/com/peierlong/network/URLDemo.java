package com.peierlong.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author elong
 * @version V1.0
 * @date 2018/8/23
 */
public class URLDemo {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com");
            /* 字节流 */
        try (InputStream in = url.openStream();
            /* 字符流 */
             InputStreamReader isr = new InputStreamReader(in, "UTF-8");
            /* 提供缓存功能 */
             BufferedReader br = new BufferedReader(isr)) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        }
    }

}
