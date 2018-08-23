package com.peierlong.network;

import java.net.InetAddress;

/**
 * @author elong
 * @version V1.0
 * @date 2018/8/23
 */
public class IPAddressTest {

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.baidu.com");
            System.out.println(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
