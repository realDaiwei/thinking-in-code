package daiwei.atguigu.inout;

import daiwei.atguigu.inout.client.InOutBoundNettyClient;

/**
 * 客户端 Test main
 * Created by Daiwei on 2021/2/25
 */
public class InOutBoundClientTestMain {

    public static void main(String[] args) {
        new InOutBoundNettyClient("127.0.0.1", 8805).run();
    }

}
