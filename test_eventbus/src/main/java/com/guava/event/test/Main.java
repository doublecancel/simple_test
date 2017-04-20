package com.guava.event.test;

import com.google.common.eventbus.EventBus;

/**
 * Created by Administrator on 2017/4/18.
 */
public class Main {


    public static void main(String[] args) {
        //同步时间通知器
        EventBus bus = new EventBus();
        bus.register(new SubEvent());//添加监听者
        bus.post("abc");//发送事件

        //异步时间通知器
//        AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(3));
//        asyncEventBus.register(new Event());
//        asyncEventBus.post("cda");
//        System.out.println("end");
    }
}
