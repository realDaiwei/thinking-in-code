package io.daiwei.shardingtable.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Daiwei on 2021/3/12
 */
@Configuration
@MapperScan("io.daiwei.shardingtable.mapper")
public class MybatisConfiguration {
}
