package com.atomikos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by Administrator on 2017/4/18.
 */
@Configuration
public class MyJdbcTemplate {

    @Resource(name = "dataSourceA")
    DataSource dataSourceA;
    @Resource(name = "dataSourceB")
    DataSource dataSourceB;

    @Bean
    public JdbcTemplate jdbcTemplateA (){
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSourceA);
        return template;
    }

    @Bean
    public JdbcTemplate jdbcTemplateB (){
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSourceB);
        return template;
    }


}
