package service;

import pojo.User;
import pojo.UserSearch;

/**
 * Created by Daiwei on 2021/3/27
 */
public interface UserService {

    User selectRandomUser(UserSearch search);

}
