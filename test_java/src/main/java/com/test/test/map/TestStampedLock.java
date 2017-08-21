package com.test.test.map;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by Administrator on 2017/8/18.
 */
public class TestStampedLock {


    private StampedLock lock = new StampedLock();

    private String str = "";

    public static TestStampedLock create(){
        return new TestStampedLock();
    }

    public static void main(String[] args) {
        TestStampedLock bean = create();

        new Thread(() -> {
            bean.read();
        }).start();

        new Thread(() -> {
            bean.write();
        }).start();


    }


    public void read(){
        Long stamp = lock.tryOptimisticRead();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(lock.validate(stamp));

        if(!lock.validate(stamp)){
            lock.readLock();
        }

        System.out.println("str : " + str);
        System.out.println("read start");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("str : " + str);
        System.out.println("read end");
        lock.tryUnlockRead();
    }

    public void write(){
        lock.writeLock();
        System.out.println("write start");
        try {
            TimeUnit.SECONDS.sleep(1);
            str = "write";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("write end");
        lock.tryUnlockWrite();
    }



}
