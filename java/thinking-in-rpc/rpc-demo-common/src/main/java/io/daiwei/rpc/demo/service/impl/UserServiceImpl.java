package io.daiwei.rpc.demo.service.impl;

import io.daiwei.rpc.demo.pojo.User;
import io.daiwei.rpc.demo.service.UserService;

/**
 * Created by Daiwei on 2021/3/20
 */
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return User.builder().id(1).userName("daiwei").age(25).build();
    }

    @Override
    public void sayHi(String name) {
        System.out.println("hi " + name + " ~");
    }

    @Override
    public void crying() {
        System.out.println("wuwuwuwu!!!");
    }

}
