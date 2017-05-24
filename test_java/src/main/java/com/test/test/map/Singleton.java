package com.test.test.map;

/**
 * Created by Administrator on 2017/5/9.
 */
public class Singleton {
    public static volatile Singleton instance;
    public static Singleton getInstance(){
        if(instance == null){
            synchronized (instance){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
