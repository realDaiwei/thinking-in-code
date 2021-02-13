package io.daiwei.aop;

import io.daiwei.aop.proxy.AopCglibProxy;
import io.daiwei.aop.service.Action;
import io.daiwei.common.UserImpl;

/**
 * Created by Daiwei on 2021/2/13
 */
public class TestMain {

    public static void main(String[] args) {
        AopCglibProxy proxy = new AopCglibProxy();
        Action user = proxy.newProxyInstance(Action.class);
        user.sayName("daiwei");
        user.sayHello("daiwei!");
    }
}
