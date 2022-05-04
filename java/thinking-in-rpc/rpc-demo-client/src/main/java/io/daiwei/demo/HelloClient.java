package io.daiwei.demo;

import io.daiwei.rpc.demo.pojo.User;
import io.daiwei.rpc.demo.service.UserService;
import io.daiwei.rpc.stub.RpcClientStubFactory;

/**
 * Created by Daiwei on 2021/3/20
 */
public class HelloClient {

    public static void main(String[] args) {
        RpcClientStubFactory stubFactory = new RpcClientStubFactory();
        UserService userService = stubFactory.createStub(UserService.class);
        User user = userService.findById(1);
        System.out.println(user);
    }
}
