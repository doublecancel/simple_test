package com.test.main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/4/18.
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "OK";
    }
}
