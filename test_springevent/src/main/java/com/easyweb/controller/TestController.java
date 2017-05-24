package com.easyweb.controller;

import com.easyweb.event.ContentEvent;
import com.easyweb.model.ModelBase;
import com.easyweb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/24.
 */
@RestController
@RequestMapping
public class TestController {


    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ApplicationEventMulticaster m;


    @GetMapping("/test")
    public String get(){
        System.out.println("=======================================================================");
        System.out.println("thread:" + Thread.currentThread().getName());
        applicationContext.publishEvent(new ContentEvent("hello world"));//同步事件机制
        System.out.println("ok......................");
        System.out.println("========================================================================");
        return "OK";
    }




    @GetMapping("/first")
    public String first(@Validated({ModelBase.First.class}) User user, BindingResult result){
        if(result.hasErrors()){
            result.getAllErrors().forEach((a) -> {
                System.out.println(a.getDefaultMessage());
            });
            return "error";
        }
        return "OK";
    }

    @GetMapping("/second")
    public String second(@Validated({ModelBase.Second.class}) User user, BindingResult result){
        if(result.hasErrors()){
            result.getAllErrors().forEach((a) -> {
                System.out.println(a.getDefaultMessage());
            });
            return "error";
        }
        return "OK";
    }


}
