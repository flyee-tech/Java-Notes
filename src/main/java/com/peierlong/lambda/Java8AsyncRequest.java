package com.peierlong.lambda;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.util.ArrayList;
import java.util.List;

/**
 * @author elong
 * @version V1.0
 * @date 2018/7/20
 */
public class Java8AsyncRequest {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add("http://www.baidu.com");
        }

        list.parallelStream().map(s -> {
            System.out.println(Thread.currentThread() + ": " + s);
            try {
                Thread.sleep(5000L);
                return HttpClients.custom().build().execute(new HttpGet(s)).getStatusLine().getStatusCode() + "";
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).forEach(System.out::println);


    }

}
