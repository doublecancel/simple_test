package test.config;


import test.config.routingDataSource.DbContextHolder;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/4/14.
 */

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDataSource {
    DbContextHolder value() default DbContextHolder.MASTER;
}
