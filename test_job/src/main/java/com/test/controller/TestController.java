package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/12.
 */
@RestController
@RequestMapping
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

}
