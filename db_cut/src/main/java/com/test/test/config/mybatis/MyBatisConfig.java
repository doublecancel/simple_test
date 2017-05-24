package com.test.test.config.mybatis;


import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import com.test.test.config.routingDataSource.DbContextHolder;
import com.test.test.config.routingDataSource.ReadWriteSplitRoutingDataSource;
import com.test.test.dao.MyBatisInterceptor;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/13.
 */

@Configuration
public class MyBatisConfig {

    @Autowired
    ApplicationContext atx;

    @javax.annotation.Resource
    DataSource master;

    @javax.annotation.Resource
    DataSource slave;
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(roundRobinDataSouceProxy());//加载数据源
        Resource[] r1 = resolver.getResources("classpath:mapper/*.xml");
        factory.setMapperLocations(r1);
        factory.setPlugins(new Interceptor[]{new MyBatisInterceptor()});
        LogFactory.useLog4JLogging();
        return factory.getObject();
    }


    public AbstractRoutingDataSource roundRobinDataSouceProxy(){
        AbstractRoutingDataSource proxy = new ReadWriteSplitRoutingDataSource();
        Map<Object,Object> targetDataResources = new HashedMap();
        targetDataResources.put(DbContextHolder.MASTER, master);
        targetDataResources.put(DbContextHolder.SLAVE, slave);
        proxy.setDefaultTargetDataSource(master);//默认源
        proxy.setTargetDataSources(targetDataResources);
        proxy.afterPropertiesSet();
        return proxy;
    }



    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        sqlSessionTemplate.clearCache();
        return sqlSessionTemplate;
    }
}
