package cn.guice.demo.service;

import cn.guice.demo.annotation.MethodAop;

import javax.annotation.Nullable;

/**
 * Created by Administrator on 2017/5/27.
 */
public class CommonInvoke {

    @MethodAop(name = "hhhhh")
    public void test(){
        System.out.println("test");
    }

    public void ok(@Nullable String name, String pwd){

    }

}
