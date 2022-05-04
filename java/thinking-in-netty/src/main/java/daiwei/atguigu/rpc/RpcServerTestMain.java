package daiwei.atguigu.rpc;

import daiwei.atguigu.rpc.server.RpcNettyServer;

/**
 * Created by Daiwei on 2021/2/27
 */
public class RpcServerTestMain {

    public static void main(String... args) {
        new RpcNettyServer(8808).run();
    }
}
