package daiwei.atguigu.tcp;

import daiwei.atguigu.tcp.client.TcpNettyClient;

/**
 * Created by Daiwei on 2021/2/27
 */
public class TcpClientTestMain {

    public static void main(String[] args) {
        new TcpNettyClient("127.0.0.1", 8801).run();
    }
}
