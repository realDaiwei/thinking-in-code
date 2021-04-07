package io.daiwei.redis.config;

import io.daiwei.redis.props.RedisClientConfigureProps;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * Created by Daiwei on 2021/4/7
 */
@Configuration
public class RedisTemplateConfiguration {

    @Resource
    private JedisPoolConfig jedisPoolConfig;

    @Resource
    private RedisClientConfigureProps props;

    public JedisConnectionFactory factory() {
        JedisClientConfiguration clientConfiguration = JedisClientConfiguration.builder().usePooling()
                .poolConfig(jedisPoolConfig).build();
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setPort(props.getConnProps().getPort());
        configuration.setDatabase(props.getConnProps().getDatabaseIdx());
        configuration.setPassword(props.getConnProps().getPassword());
        configuration.setHostName(props.getConnProps().getHost());
        configuration.setUsername(props.getConnProps().getUsername());
        JedisConnectionFactory factory = new JedisConnectionFactory(configuration, clientConfiguration);
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    @ConditionalOnProperty(prefix = "daiwei-starter.ds.redis", name = "client-type", havingValue = "redisTemplate")
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
}
