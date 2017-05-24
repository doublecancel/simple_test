package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Administrator on 2017/5/12.
 */
@SpringBootApplication
@ImportResource("beans.xml")
public class Main {
    public static void main(String[] args) {

        System.getProperties().put("appName", "collectApp");
        SpringApplication.run(Main.class, args);


    }
}
