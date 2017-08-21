package com.test.test.map;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/18.
 */
public class SynchronizedTest {


    public static synchronized void test(){
            System.out.println("in");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        SynchronizedTest bean = new SynchronizedTest();
        SynchronizedTest bean1 = new SynchronizedTest();
        SynchronizedTest bean2 = new SynchronizedTest();
//        IntStream.range(1, 4).forEach(a -> {
//            new Thread(() -> {
//                bean.test();
//            }).start();
//        });
        new Thread(() -> {
            bean.test();
        }).start();
        new Thread(() -> {
            bean1.test();
        }).start();
        new Thread(() -> {
            bean2.test();
        }).start();
    }


}
