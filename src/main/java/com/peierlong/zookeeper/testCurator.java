package com.peierlong.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 包名: com.peierlong.zookeeper
 * 创建人 : Elong
 * 时间: 22/03/2017 3:21 PM
 * 描述 : 开源Zookeeper客户端Curator使用示例
 */
public class testCurator {

    /**
     * 同步相关的API
     */
    @Test
    public void synTest() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //使用Fluent风格的API接口来创建一个Zookeeper客户端
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("base")
                .build();
        client.start();

        //创建一个节点
        client.create().forPath("/base");
        client.create().forPath("/base1", "123".getBytes());
        //创建一个临时节点，并递归创建父节点(除了path对应的数据节点为临时节点，其余的父节点都是持久节点)
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/a/b/c/d");

        //删除节点
        client.delete()
                .deletingChildrenIfNeeded()
                .withVersion(0)
                .forPath("/path");
        client.delete().guaranteed().forPath("/path");

        //读取一个数据，并获得该节点的stat
        client.getData().storingStatIn(new Stat()).forPath("/path");

        //更新数据
        client.setData().forPath("/path");

    }

    /**
     * 异步相关的API
     */
    @Test
    public void aSynTest() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //使用Fluent风格的API接口来创建一个Zookeeper客户端
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        final CountDownLatch semaphore = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(2);


        client.start();
        System.out.println("Main Thread : " + Thread.currentThread().getName());

        client.create().creatingParentsIfNeeded().inBackground(
                new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        System.out.println("event1[code : " + curatorEvent.getResultCode() + ", type : " + curatorEvent.getType() + "]");
                        System.out.println("当前线程1 : " + Thread.currentThread().getName());
                        semaphore.countDown();
                    }
                }
        , executorService).forPath("/asynchronous/test1", "init".getBytes());

        client.create().creatingParentsIfNeeded().inBackground(
                new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        System.out.println("event2[code : " + curatorEvent.getResultCode() + ", type : " + curatorEvent.getType() + "]");
                        System.out.println("当前线程2 : " + Thread.currentThread().getName());
                        semaphore.countDown();
                    }
                }
        ).forPath("/asynchronous/test1", "init".getBytes());

        semaphore.await();
        executorService.shutdown();

    }

}
