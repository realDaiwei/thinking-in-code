package io.daiwei.redis.config;

import io.daiwei.redis.props.RedisClientConfigureProps;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Created by Daiwei on 2021/4/10
 */
@Configuration
public class RedissonConfiguration {

    @Resource
    private RedisClientConfigureProps props;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        String addr = "redis://" + props.getConnProps().getHost() + ":" + props.getConnProps().getPort();
        config.useSingleServer().setAddress(addr)
                .setDatabase(props.getConnProps().getDatabaseIdx())
                .setPassword(props.getConnProps().getPassword())
                .setUsername(props.getConnProps().getUsername());
        return Redisson.create(config);
    }
}
