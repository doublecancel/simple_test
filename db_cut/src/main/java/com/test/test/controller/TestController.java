package com.test.test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.test.test.domain.TestDomain;
import com.test.test.service.ITestService;

/**
 * Created by Administrator on 2017/1/13.
 */
@Controller
public class TestController {
    @Autowired
    ITestService service;

    @RequestMapping("/com/test/test")
    @ResponseBody
    String test() {
        System.out.println(service.getById(10L));
        return "aaa";
    }

    @RequestMapping("/test1")
    @ResponseBody
    String test1() {
        TestDomain domain = new TestDomain();
        domain.setId(10L);
        domain.setGender(1);
        domain.setIp("11111111");
        domain.setNickname("dddddddddddddddd");
        domain.setName("ddddddddddddddddd");
        service.update(domain);
        return "aaa";
    }


}
