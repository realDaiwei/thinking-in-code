package io.daiwei.enttiy.biz;

import lombok.Data;

/**
 * Created by Daiwei on 2021/4/7
 */
@Data
public class User {

    private String name;

    private Integer age;

    private String mobile;

    private String addr;

    public User() {}

    public User(String name, Integer age, String mobile, String addr) {
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.addr = addr;
    }
}
