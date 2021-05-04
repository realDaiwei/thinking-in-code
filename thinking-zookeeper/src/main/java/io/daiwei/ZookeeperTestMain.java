package io.daiwei;

import io.daiwei.service.UserServiceImpl;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Daiwei on 2021/4/17
 */
public class ZookeeperTestMain {

    private static CuratorFramework client;

    public static void main(String[] args) throws Exception {
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            client.close();
//        }));

//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            System.out.println("close");
//        }));
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder().connectString("localhost:2181").namespace("zk-demo").retryPolicy(retryPolicy).build();
        client.start();
//        CuratorCache cache = CuratorCache.builder(client, File.separator).build();
//        CuratorCacheListener listener = CuratorCacheListener.builder().forPathChildrenCache(File.separator, client, new PathChildrenCacheListener() {
//            @Override
//            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
//                System.out.println("wa!!!");
//            }
//        }).forInitialized(() -> {
//            System.out.println("init");
//        }).build();
//        cache.listenable().addListener(listener);
//        cache.start();
        try {
            client.create().withMode(CreateMode.EPHEMERAL).forPath("/" + UserServiceImpl.class.getCanonicalName());
//            client.getChildren().usingWatcher((Watcher) watchedEvent -> {
//                System.out.println("watch!");
//            }).forPath("/");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        System.out.println("hello");
//        while (true) {}
//        cache.close();
//        client.getZookeeperClient().getZooKeeper().close();
        client.close();
    }



}
