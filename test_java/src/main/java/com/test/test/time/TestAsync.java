package com.test.test.time;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 2017/6/26.
 */
public class TestAsync {
    public static void main(String[] args) throws Exception{


        CyclicBarrier barrier = new CyclicBarrier(10);

        for(int a = 0; a < 10; a++){
            new Thread(() -> {
                System.out.println("enter thread:" + Thread.currentThread().getName() + "");
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("exit thread:" + Thread.currentThread().getName() + "");
            }).start();
        }

        CountDownLatch latch = new CountDownLatch(3);


    }




}
