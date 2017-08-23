package com.guava.event.test;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;

import java.util.List;

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


        test();


    }



    public static void test(){

        List<Integer> data = Lists.newArrayList(1, 2, 3, null, 4);

        Integer t = Iterables.find(Iterables.cycle(null, 2, 3), Predicates.notNull());

        System.out.println(t);
    }





}
