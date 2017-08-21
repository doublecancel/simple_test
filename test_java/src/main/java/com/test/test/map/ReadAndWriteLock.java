package com.test.test.map;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2017/8/18.
 */
public class ReadAndWriteLock {

    public static ReadAndWriteLock create(){
        return new ReadAndWriteLock();
    }

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private AtomicInteger ai = new AtomicInteger(0);

    public static void main(String[] args) {
        ReadAndWriteLock bean = create();

        new Thread(() -> {
            bean.testReadLock();
        }).start();

        IntStream.range(1, 100).forEach(a -> {
            new Thread(() -> {
                bean.testWriteLock();
            }).start();
        });




    }

    public void testReadLock(){

        if(!readLock.tryLock()){
            System.out.println("获取读锁失败" + ai.incrementAndGet());
            return;
        }


        System.out.println("ai : " + ai.incrementAndGet());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        readLock.unlock();


    }

    public void testWriteLock(){

        if(!writeLock.tryLock()){
            System.out.println("获取写锁失败");
            return;
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }




    }
}
