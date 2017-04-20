package com.test.main;

import com.test.main.annotation.EnableTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2017/4/18.
 */
@SpringBootApplication
@EnableTest
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
