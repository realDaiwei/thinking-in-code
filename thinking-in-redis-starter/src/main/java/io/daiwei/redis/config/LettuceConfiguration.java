package io.daiwei.redis.config;

import io.daiwei.redis.props.RedisClientConfigureProps;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.resource.ClientResources;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisCommand;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * Created by Daiwei on 2021/4/8
 */
@Configuration
@ConditionalOnProperty(prefix = "daiwei-starter.ds.redis", name = "client-type", havingValue = "lettuce")
public class LettuceConfiguration {

    @Resource
    private RedisClientConfigureProps props;

    public RedisClient redisClient() {
        RedisURI redisURI = RedisURI.builder().withHost(props.getConnProps().getHost())
//                .withPassword(props.getConnProps().getPassword())
                .withDatabase(props.getConnProps().getDatabaseIdx())
                .withPort(props.getConnProps().getPort()).build();
        return RedisClient.create(redisURI);
    }

    @Bean
    public RedisAsyncCommands<String, String> asyncClient() {
        return redisClient().connect().async();
    }

    @Bean
    public RedisCommands<String, String> syncClient() {
        return redisClient().connect().sync();
    }
}
