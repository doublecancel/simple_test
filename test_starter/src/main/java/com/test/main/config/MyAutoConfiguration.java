package com.test.main.config;

import org.springframework.context.annotation.Bean;

/**
 * Created by Administrator on 2017/4/18.
 */
//@Configuration
//@EnableWebMvc
public class MyAutoConfiguration {
    public MyAutoConfiguration(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
    @Bean
    public Object object(){
        return null;
    }
}
