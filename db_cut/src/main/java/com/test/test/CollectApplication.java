/**
 * Created by mt on 16/5/16 下午2:15.
 */

package com.test.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author mt
 */
@SpringBootApplication
public class CollectApplication {

    public static void main(String[] args) {
        System.getProperties().put("appName", "collectApp");

        SpringApplication.run(CollectApplication.class, args);
    }


    @Autowired
    ApplicationContext ac;



}