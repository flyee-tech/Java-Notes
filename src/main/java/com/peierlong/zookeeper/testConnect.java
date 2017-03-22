package com.peierlong.zookeeper;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * 包名: com.peierlong.zookeeper
 * 创建人 : Elong
 * 时间: 22/03/2017 2:11 PM
 * 描述 : 测试连接到zookeeper
 */
public class testConnect {

    private static CountDownLatch connectCountDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 10000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("watchedEvent = " + watchedEvent);
                if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                    connectCountDownLatch.countDown();
                }
            }
        });

        System.out.println("回调前state = " + zk.getState());
        connectCountDownLatch.await();
        System.out.println("回调后state = " + zk.getState());

        System.out.println("连接创建完毕！");

        String path = zk.create("/zk-test", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Success create znode " + path);


    }


}
