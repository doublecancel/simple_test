/*
 * 所属项目: hlms.itms
 * 创建人: mt
 * 创建时间: 2016-05-27 01:11
 */

package com.test.test.config.mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mt on 2016-05-27 01:11.
 */
@Configuration
public class MyBatisConfigX {
    private static final String SQL_SESSION_FACTORY_BEAN_NAME = "sqlSessionFactory";

    /**
     * 该bean不受config加载顺序不可控制, 暂时将配置直接写在代码中, 以后有时间通过手动读取yaml加载配置
     *
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("gg.test.dao");
        configurer.setSqlSessionFactoryBeanName(SQL_SESSION_FACTORY_BEAN_NAME);
        return configurer;
    }
}