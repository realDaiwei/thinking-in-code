package io.daiwei.redis.props;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Daiwei on 2021/4/6
 */
@Configuration
@ConfigurationProperties(prefix = "daiwei-starter.ds.redis")
public class RedisClientConfigureProps {

    private String clientType;

    private RedisConnProps connProps;

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public RedisConnProps getConnProps() {
        return connProps;
    }

    public void setConnProps(RedisConnProps connProps) {
        this.connProps = connProps;
    }
}
