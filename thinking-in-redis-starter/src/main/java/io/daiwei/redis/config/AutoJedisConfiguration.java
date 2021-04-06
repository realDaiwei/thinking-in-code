package io.daiwei.redis.config;

import io.daiwei.redis.props.JedisConfigureProps;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.time.Duration;
import java.util.Optional;

/**
 * Created by Daiwei on 2021/4/6
 */
@Configuration
public class AutoJedisConfiguration {

    @Resource
    private JedisConfigureProps props;

    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(props.getMaxIdle());
        jedisPoolConfig.setMinIdle(props.getMinIdle());
        jedisPoolConfig.setMaxTotal(props.getMaxTotal());
        return jedisPoolConfig;
    }

    public JedisConnectionFactory factory() {
        JedisClientConfiguration clientConfiguration = JedisClientConfiguration.builder().usePooling()
                .poolConfig(jedisPoolConfig()).build();
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setPort(props.getPort());
        configuration.setDatabase(props.getDatabaseIdx());
        configuration.setPassword(props.getPassword());
        configuration.setHostName(props.getHost());
        configuration.setUsername(props.getUserName());
        JedisConnectionFactory factory = new JedisConnectionFactory(configuration, clientConfiguration);
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setConnectionFactory(factory());
        template.afterPropertiesSet();
        return template;
    }

}
