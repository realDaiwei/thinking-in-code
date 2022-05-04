package mapper;

import pojo.User;

/**
 * Created by Daiwei on 2021/3/27
 */
public interface UserMapper {

    User selectById(Integer id);
}
