package com.test.test.strategy;

/**
 * Created by Administrator on 2017/8/7.
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void test(){
        this.strategy.test();
    }

}
