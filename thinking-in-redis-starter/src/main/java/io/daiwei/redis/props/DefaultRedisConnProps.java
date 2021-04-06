package io.daiwei.redis.props;

import lombok.Data;

/**
 * Created by Daiwei on 2021/4/6
 */
public class DefaultRedisConnProps {

    private String host;

    private String userName;

    private String password;

    private int databaseIdx;

    private int port;

    private int maxIdle;

    private int minIdle;

    private int maxTotal;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public int getDatabaseIdx() {
        return databaseIdx;
    }

    public void setDatabaseIdx(int databaseIdx) {
        this.databaseIdx = databaseIdx;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }
}
