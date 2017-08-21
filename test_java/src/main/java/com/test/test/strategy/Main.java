package com.test.test.strategy;

/**
 * Created by Administrator on 2017/8/7.
 */
public class Main {

    public static void main(String[] args) {
        Context context0 = new Context(new Strategy0());
        Context context1 = new Context(new Strategy1());
        Context context2 = new Context(new Strategy2());
        context0.test();
        context1.test();
        context2.test();

    }

}
