package daiwei.atguigu.rpc;

import daiwei.atguigu.rpc.client.RpcNettyClient;
import daiwei.atguigu.rpc.common.HelloService;
import daiwei.atguigu.rpc.common.HelloServiceImpl;

/**
 * Created by Daiwei on 2021/2/27
 */
public class RpcClientTestMain {

    private static final String PROVIDER_NAME = "HelloService#hello#";

    public static void main(String... args) {
        RpcNettyClient nettyClient = new RpcNettyClient("127.0.0.1", 8808);

        HelloService bean = (HelloService) nettyClient.getBean(HelloService.class, PROVIDER_NAME);
        String res = bean.hello("daiwei");
        System.out.println(res);
    }
}
