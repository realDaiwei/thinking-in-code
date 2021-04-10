package io.daiwei.redis.config;

import io.daiwei.redis.props.RedisClientConfigureProps;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultScriptExecutor;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * Created by Daiwei on 2021/4/7
 */
@Configuration
@ConditionalOnProperty(prefix = "daiwei-starter.ds.redis", name = "client-type", havingValue = "redisTemplate")
public class RedisTemplateConfiguration {

    @Resource
    private JedisPoolConfig jedisPoolConfig;

    @Resource
    private RedisClientConfigureProps props;

    public RedisStandaloneConfiguration standaloneConfiguration() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setPort(props.getConnProps().getPort());
        configuration.setDatabase(props.getConnProps().getDatabaseIdx());
        configuration.setPassword(props.getConnProps().getPassword());
        configuration.setHostName(props.getConnProps().getHost());
        configuration.setUsername(props.getConnProps().getUsername());
        return configuration;
    }

    public JedisConnectionFactory jedisFactory() {
        JedisClientConfiguration clientConfiguration = JedisClientConfiguration.builder().usePooling()
                .poolConfig(jedisPoolConfig).build();
        JedisConnectionFactory factory = new JedisConnectionFactory(standaloneConfiguration(), clientConfiguration);
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setScriptExecutor(new DefaultScriptExecutor<>(template));
        template.afterPropertiesSet();
        return template;
    }
}
