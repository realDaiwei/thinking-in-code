package io.daiwei.service;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Created by Daiwei on 2021/4/21
 */
public class ZkTest01Main {

    private final String hosts = "localhost:2181";
    private final String namespace = "zk-demo";
    private int sessionTimeoutMs = 10000;
    private int connectionTimeoutMs = 15000;
    private int maxWaitTimeMs = 10000;

    private CuratorFramework client;

    public CuratorFramework getClient() throws InterruptedException {
        /*
            重试策略使用指数补偿重试机制 arg1 = 基础等待时间Ms 如果重试失败 第二次的时长会基于baseSleepTime指数增长
            arg2 = 最大重试次数 源码可以看到最大重试29次 大于29次的会默认等于29
          */
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        /*
            指定命名空间来隔离不同环境的节点，这里我用来隔离不同项目的节点
         */
        client = CuratorFrameworkFactory.builder()
                .connectString(hosts)
                .sessionTimeoutMs(sessionTimeoutMs)
                .connectionTimeoutMs(connectionTimeoutMs)
                .retryPolicy(retryPolicy)
                .namespace(namespace)
                .build();
        client.start();
        startListener();
        destroyOnFailure();
        return client;
    }

    /**
     * close client manually
     *
     * @throws InterruptedException e
     */
    private void destroyOnFailure() throws InterruptedException {
        // block & wait until connected or maxWaitTime
        // if client is not null close and shut down
        if (!client.blockUntilConnected(maxWaitTimeMs, TimeUnit.MILLISECONDS) && client != null) {
            client.close();
            System.exit(0);
        }
    }


    /**
     * create a TreeCacheListener for childEvent listening
     */
    public void startListener() {
        //由于new TreeCacheListener/NodeCache/PathChildrenCache 新版本已经过时，
        //这里使用curatorCache提供的工厂方法 构造treeCacheListener
        CuratorCacheListener listener = CuratorCacheListener
                .builder()
                // 3 types of cache listener, choose one in case
                .forPathChildrenCache(File.separator, client, (c, e) -> {
                    // child node listener
                    switch (e.getType()) {
                        default:
                            break;
                    }
                })
                .forNodeCache(() -> {
                    //do something when node changed
                })
                .forTreeCache(client, (c, e) -> {
                    System.out.println("status changed: " + e.getType());
                    switch (e.getType()) {
                        case INITIALIZED:
                            whenInit(e);
                            break;
                        case NODE_ADDED:
                            whenAdded(e);
                            break;
                        case NODE_UPDATED:
                            whenUpdated(e);
                            break;
                        case NODE_REMOVED:
                            whenRemoved(e);
                            break;
                        default:
                            break;
                    }
                })
                .build();
        CuratorCache cache = CuratorCache.builder(client, File.separator).build();
        System.out.println("CuratorCache building complete ,listener path : " + File.separator + namespace);
        cache.listenable().addListener(listener);
        cache.start();
    }

    private void whenInit(TreeCacheEvent e) {
        System.out.println("TreeCacheListener initialized, status :" + e.getType());
    }

    private void whenAdded(TreeCacheEvent e) {
        Stat stat = e.getData().getStat();
        System.out.println("node stat :" + stat);
        System.out.println("node created: " + e.toString());
    }

    private void whenUpdated(TreeCacheEvent e) {
        System.out.println("node update: Old : "+ e.getOldData().getPath()+"-"+ e.getOldData().getData() +" " +
                        ",New :"+ e.getData().getPath() +"-" + e.getData().getData());
    }

    private void whenRemoved(TreeCacheEvent e) {
        System.out.println("node removed:"+ e.getData().getPath() +"-" + e.getData().getData());
    }


    public static void main(String[] args) throws Exception {
        ZkTest01Main main = new ZkTest01Main();
        CuratorFramework client = main.getClient();
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/daiwei", "hello".getBytes(StandardCharsets.UTF_8));
        while(true) {}
    }
}
