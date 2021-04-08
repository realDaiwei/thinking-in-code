package io.daiwei.redis.config;

import io.daiwei.redis.props.RedisClientConfigureProps;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;




/**
 * Created by Daiwei on 2021/4/6
 */
@Configuration
public class JedisConfiguration {

    @Resource
    private RedisClientConfigureProps props;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(props.getConnProps().getMaxIdle());
        jedisPoolConfig.setMinIdle(props.getConnProps().getMinIdle());
        jedisPoolConfig.setMaxTotal(props.getConnProps().getMaxTotal());
        return jedisPoolConfig;
    }

    @Bean
    @ConditionalOnProperty(prefix = "daiwei-starter.ds.redis", name = "client-type", havingValue = "jedis")
    public JedisPool jedisPool() {
        return props.getConnProps() != null && (props.getConnProps().getPassword() != null && props.getConnProps().getUsername() != null )
                ? new JedisPool(jedisPoolConfig(), props.getConnProps().getHost(), props.getConnProps().getPort(), props.getConnProps().getTimeout()) :
                new JedisPool(jedisPoolConfig(), props.getConnProps().getHost(), props.getConnProps().getPort(), props.getConnProps().getTimeout(),
                        props.getConnProps().getUsername(), props.getConnProps().getPassword(), props.getConnProps().getDatabaseIdx());
    }

}
