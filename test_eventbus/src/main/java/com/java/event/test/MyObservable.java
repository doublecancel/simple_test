package com.java.event.test;

import java.util.Observable;

/**
 * Created by Administrator on 2017/4/18.
 */
public class MyObservable extends Observable {

    private int id;

    public void setData(int id){
        this.id = id;
    }

    public static void main(String[] args) {
        MyObservable o = new MyObservable();//被观察对象
        o.addObserver((obj, arg) -> System.out.println("update1"));//添加观察者1
        o.addObserver((obj, arg) -> System.out.println("update2"));//添加观察者1
        o.setData(1);//执行update操作
        o.setChanged();o.notifyObservers();//通知观察者
    }
}
