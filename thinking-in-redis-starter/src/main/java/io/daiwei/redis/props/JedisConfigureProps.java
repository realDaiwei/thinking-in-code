package io.daiwei.redis.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Daiwei on 2021/4/6
 */
@Configuration
@ConfigurationProperties(prefix = "daiwei-starter.datasource.jedis")
public class JedisConfigureProps extends DefaultRedisConnProps {

}
