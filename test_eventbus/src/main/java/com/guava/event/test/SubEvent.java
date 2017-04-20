package com.guava.event.test;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * Created by Administrator on 2017/4/18.
 */
public class SubEvent extends Event{
//    @Subscribe
//    public void sub(String message){
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("sub:" + message);
//    }
//    @Subscribe
//    public void sub1(String message){
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("sub1:" + message);
//    }

    //无任何观察者是调用
    @Subscribe
    public void sub1(DeadEvent event){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SubEvent DeadEvent:");
    }









}
