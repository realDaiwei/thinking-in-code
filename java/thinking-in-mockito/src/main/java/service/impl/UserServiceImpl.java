package service.impl;

import mapper.UserMapper;
import pojo.User;
import pojo.UserSearch;
import service.UserService;

import java.util.Random;

/**
 * Created by Daiwei on 2021/3/27
 */
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User selectRandomUser(UserSearch search) {
        Random rnd = new Random();
        if (search == null) {
            return null;
        }
        int idx = rnd.nextInt(search.getId());
        return userMapper.selectById(idx);
    }
}
