package daiwei.atguigu.inout;

import daiwei.atguigu.inout.server.InOutBoundNettyServer;

/**
 * 服务端TestMain
 * Created by Daiwei on 2021/2/25
 */
public class InOutBoundServerTestMain {

    public static void main(String[] args) {
        new InOutBoundNettyServer(8805).run();
    }

}
