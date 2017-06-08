package com.test.config.properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by Administrator on 2017/6/8.
 */
public final class DbCreateor {

    public static DataSource create(){
        ComboPooledDataSource source = new ComboPooledDataSource();
        try {
            source.setDriverClass("org.mariadb.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        source.setJdbcUrl("jdbc:mysql://192.168.200.120:3306/test?useUnicode=true&characterEncoding=utf-8");
        source.setUser("root");
        source.setPassword("root");
        source.setPreferredTestQuery("select 1");
        source.setInitialPoolSize(7);
        source.setIdleConnectionTestPeriod(60);
        source.setMaxIdleTime(60);
        source.setMaxPoolSize(14);
        return source;
    }




}
