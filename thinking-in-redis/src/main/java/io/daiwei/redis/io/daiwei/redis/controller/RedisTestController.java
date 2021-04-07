package io.daiwei.redis.io.daiwei.redis.controller;

import io.daiwei.enttiy.biz.User;
import io.daiwei.enttiy.springboot.ReturnT;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * Created by Daiwei on 2021/4/6
 */
@RestController
@RequestMapping("hello")
public class RedisTestController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

//    @Resource
//    private JedisPool jedisPool;

    @GetMapping("/test")
    public ReturnT<User> test() {
//        jedisPool.getResource().set("daiwei-from-jedis", "hello");
        redisTemplate.opsForValue().set("daiwei-from-redisTemplate", "hello");
        return ReturnT.ok(new User("daiwei", 25, "158^__^7251", "sh"));
    }

}
