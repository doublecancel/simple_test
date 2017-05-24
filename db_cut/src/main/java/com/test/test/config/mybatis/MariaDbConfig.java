package com.test.test.config.mybatis;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by mt on 2016/5/20.
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(DataSourceProperties.class)
public class MariaDbConfig extends DataSourceAutoConfiguration {
    private final static String TEST_QUERY = "SELECT 1";

    @Autowired
    private DataSourceProperties properties;

    @Bean
    @Primary
    public DataSource master() throws PropertyVetoException {
        ComboPooledDataSource source = new ComboPooledDataSource();
        source.setDriverClass(properties.getDriverClassName());
//        source.setJdbcUrl(properties.getUrl());
        source.setJdbcUrl("jdbc:mysql://192.168.200.249:3306/edaishou?useUnicode=true&characterEncoding=utf-8");
        source.setUser(properties.getUsername());
        source.setPassword(properties.getPassword());
        source.setPreferredTestQuery(TEST_QUERY);
        source.setInitialPoolSize(7);
        source.setIdleConnectionTestPeriod(60);
        source.setMaxIdleTime(60);
        source.setMaxPoolSize(14);
        return source;
    }
    @Bean
    public DataSource slave() throws PropertyVetoException {
        ComboPooledDataSource source = new ComboPooledDataSource();
        source.setDriverClass(properties.getDriverClassName());
        source.setJdbcUrl("jdbc:mysql://192.168.200.120:3306/edaishou?useUnicode=true&characterEncoding=utf-8");
        source.setUser(properties.getUsername());
        source.setPassword(properties.getPassword());
        source.setPreferredTestQuery(TEST_QUERY);
        source.setInitialPoolSize(7);
        source.setIdleConnectionTestPeriod(60);
        source.setMaxIdleTime(60);
        source.setMaxPoolSize(14);
        return source;
    }

}
