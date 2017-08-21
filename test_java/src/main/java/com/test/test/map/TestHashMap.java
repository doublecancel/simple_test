package com.test.test.map;

import java.util.HashMap;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/5/8.
 */
public class TestHashMap {


    /**
     * hashmap是基于map接口的实现
     * 提供了所有可选的map操作接口
     * 允许key和value为null
     * hashmap和hashtable的唯一不同hashmap是线程不安全的并且允许null
     * 不能保证元素顺序一成不变
     * hashmap提供了较好的性能
     * 所以不要将容量和参数因子设置过大，对于hashmap性能有关键影响
     * hashmap构造函数有两个参数：capacity和loadFactor
     * capacity：容器中桶的数量
     * loadFactor:参数因子
     * 当hashmap中的数量超过初始数量，hashmap会重新创建内部结构，增加容量为原来的两倍
     * 转同步map的方法：Collections.synchronizedMap(map)将hashmap转成同步map
     * 在迭代hashmap时修改会报ConcurrentModificationException，除非使用Iterator
     * @param args
     */


    public static void main(String[] args) {


        //当超过最大线程池数量，则抛出异常
        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        //当超过最大线程池数量，直接执行run方法，如果线程池关闭则舍弃该线程。
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        //当超过最大线程池数量，舍弃掉等待队列中的最后一个线程
        ThreadPoolExecutor.DiscardOldestPolicy discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();
        //如果超过最大线程池数量，直接舍弃掉。
        ThreadPoolExecutor.DiscardPolicy discardPolicy = new ThreadPoolExecutor.DiscardPolicy();


        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,
                5,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory(),
                abortPolicy
        );
        for(int a = 0 ; a < 10; a++){
            poolExecutor.execute(() -> {
                System.out.println("===========start===========");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("=========end=========");
            });
        }

        ExecutorService service = Executors.newFixedThreadPool(3);
        ExecutorService service1 = Executors.newCachedThreadPool();
        ExecutorService service2 = Executors.newScheduledThreadPool(3);
        ExecutorService service3 = Executors.newSingleThreadExecutor();

        service.execute(() -> {

        });

    }

    public static String test(){

        HashMap map = new HashMap();
        return "aaa";
    }
}
