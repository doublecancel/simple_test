package com.test.test.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

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


        Map<String, String> map = new HashMap<String, String>();


        map.put("a", "b");

        Map<String, String> cmap = new ConcurrentHashMap<>();

        AtomicLong atomicLong = new AtomicLong();






    }
}
