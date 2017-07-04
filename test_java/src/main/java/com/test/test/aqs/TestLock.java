package com.test.test.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2017/7/3.
 */
public class TestLock {

    private static String str = "";

    public static void main(String[] args) {
        TestLock test = new TestLock();

        new Thread(() -> {
            test.read();
        }).start();
        new Thread(() -> {
            test.read();
        }).start();

    }
    Lock lock = new ReentrantLock();

    ReentrantReadWriteLock lock1 = new ReentrantReadWriteLock();

    public String read(){

        lock.lock();
        System.out.println("当前线程：" + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        return this.str;
    }

    public void write(String c){
        this.str = c;
    }


}
