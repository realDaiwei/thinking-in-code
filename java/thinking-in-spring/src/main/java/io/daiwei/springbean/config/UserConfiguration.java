package io.daiwei.springbean.config;

import io.daiwei.field.SpringBean;
import io.daiwei.springbean.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Created by Daiwei on 2021/2/13
 */
@Configuration
public class UserConfiguration {

//    @Resource
//    private SpringBean bean;

    @Bean("user-by-annotation")
    public User userFromAnnotation() {
//        bean.printName();
        return new User("daiwei", 6, "wh");
    }
}
