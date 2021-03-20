package io.daiwei.rpc.demo.service.impl;

import io.daiwei.rpc.demo.pojo.User;
import io.daiwei.rpc.demo.service.UserService;

/**
 * Created by Daiwei on 2021/3/20
 */
public class UserServiceImpl implements UserService {

    @Override
    public User findById(Integer id) {
        return User.builder().id(1).userName("daiwei").age(25).build();
    }

}
