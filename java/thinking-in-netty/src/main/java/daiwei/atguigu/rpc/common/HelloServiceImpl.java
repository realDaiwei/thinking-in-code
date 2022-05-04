package daiwei.atguigu.rpc.common;

/**
 * Created by Daiwei on 2021/2/27
 */
public class HelloServiceImpl implements HelloService{

    @Override
    public String hello(String name) {
        return "hello " + name + " welcome to rpc base on netty!";
    }
}
