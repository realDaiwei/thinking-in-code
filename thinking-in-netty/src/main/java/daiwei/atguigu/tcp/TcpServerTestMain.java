package daiwei.atguigu.tcp;

import daiwei.atguigu.tcp.server.TcpNettyServer;

/**
 * Created by Daiwei on 2021/2/27
 */
public class TcpServerTestMain {

    public static void main(String[] args) {
        new TcpNettyServer(8801).run();
    }
}
