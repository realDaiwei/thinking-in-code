package io.daiwei.redis.io.daiwei.redis.controller;

import io.daiwei.enttiy.biz.User;
import io.daiwei.enttiy.springboot.ReturnT;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Daiwei on 2021/4/6
 */
@RestController
@RequestMapping("hello")
public class RedisTestController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;
//
//    @Resource
//    private JedisPool jedisPool;
//
//    @Resource
//    private RedisCommands<String, String> redisCommands;

    @GetMapping("/test")
    public ReturnT<User> test() {
//        jedisPool.getResource().set("daiwei-from-jedis", "hello");
//        redisTemplate.opsForValue().set("daiwei-from-redisTemplate", "hello");
//        String ping = redisCommands.ping();
//        System.out.println(ping);
//        List<Object> command = redisCommands.command();
//        for (Object o : command) {
//            System.out.println(o);
//        }
//        SetArgs args = SetArgs.Builder.nx().px(3000);
//        String hello = redisCommands.set("daiwei-from-lettuce", "hello", args);
//        System.out.println(hello);
//        hello = redisCommands.set("daiwei-from-lettuce", "hello", args);
//        System.out.println(hello);
        return ReturnT.ok(new User("daiwei", 25, "158^__^7251", "sh"));
    }

}
