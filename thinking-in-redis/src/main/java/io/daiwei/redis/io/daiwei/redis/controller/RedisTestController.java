package io.daiwei.redis.io.daiwei.redis.controller;

import io.daiwei.enttiy.biz.User;
import io.daiwei.enttiy.springboot.ReturnT;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.sync.RedisCommands;
import org.redisson.api.RIdGenerator;
import org.redisson.api.RLock;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Daiwei on 2021/4/6
 */
@RestController
@RequestMapping("hello")
public class RedisTestController {

    private static final String REDIS_LOCK = "redis_lock";

    private static final String REDIS_ID_GENERATOR= "redis_id_generator";

    private static final String REDIS_CACHE_MAP = "redis_cache_map";

    private static final String REDIS_CNT_KEY = "redis_sub_cnt";

    private static final String CNT_SUB_LUA = "if redis.call('EXISTS', KEYS[1]) == 1 and redis.call('GET', KEYS[1]) > ARGV[1] then redis.call('decr', KEYS[1])  return 'true' else  return 'false' end";

    @Resource
    private RedisTemplate<String, String> redisTemplate;
//
//    @Resource
//    private JedisPool jedisPool;
//
//    @Resource
//    private RedisCommands<String, String> redisCommands;

    @Resource
    private RedissonClient redisClient;

    private Integer total = 100;

    @GetMapping("/test")
    public ReturnT<User> test() throws InterruptedException {
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
        RLock lock = redisClient.getLock(REDIS_LOCK);
        try {
            lock.lock();
            System.out.println("get-lock");
            TimeUnit.SECONDS.sleep(40);
        } finally {
            lock.unlock();
            System.out.println("release-lock");
        }

        return ReturnT.ok(new User("daiwei", 25, "158^__^7251", "sh"));
    }

    @GetMapping("/desc")
    public ReturnT<Void> count() {
        RLock lock = redisClient.getLock(REDIS_LOCK);
        try {
            if (total >= 0) {
                lock.lock();
                if (total > 0) {
                    System.out.println(total);
                    total--;
                }
            }
        } finally {
            lock.unlock();
        }
        return ReturnT.ok(null);
    }

    @GetMapping("id-gen")
    public ReturnT<Void> idGen() {
        RIdGenerator idGenerator = redisClient.getIdGenerator(REDIS_ID_GENERATOR);
        System.out.println(idGenerator.nextId());
        return ReturnT.ok();
    }

    @GetMapping("/put-map")
    public ReturnT<Void> testMap() {
        RIdGenerator idGenerator = redisClient.getIdGenerator(REDIS_ID_GENERATOR);
        RMapCache<String, String> mapCache = redisClient.getMapCache(REDIS_CACHE_MAP);
        String key = idGenerator.nextId() + "";
        System.out.println(key);
        mapCache.fastPut(key, "daiwei", 1, TimeUnit.HOURS);
        return ReturnT.ok();
    }


    @GetMapping("/map-all")
    public ReturnT<List<String>> getStrFromMap() {
        RMapCache<String, String> mapCache = redisClient.getMapCache(REDIS_CACHE_MAP);
        List<String> res = new ArrayList<>();
        mapCache.entrySet().forEach(e -> res.add(e.getKey() + "::" + e.getValue()));
        return ReturnT.ok(res);
    }


    /**
     * 扣减库存
     * @return
     */
    @GetMapping("/sub-cnt")
    public ReturnT<Object> subCnt() {
        List<String> list = Collections.singletonList(REDIS_CNT_KEY);
        String res = redisTemplate.execute(RedisScript.of(CNT_SUB_LUA, String.class), list, String.valueOf(0));
        if (Boolean.valueOf(res)) {
            System.out.println("cnt success!");
        }
        return ReturnT.ok(res);
    }


}
