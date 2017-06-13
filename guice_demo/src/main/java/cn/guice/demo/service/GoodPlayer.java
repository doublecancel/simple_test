package cn.guice.demo.service;

/**
 * Created by Administrator on 2017/5/26.
 */
public class GoodPlayer implements Player {
    public void bat() {
        System.out.println("I can hit any ball");
    }

    public void bowl() {
        System.out.println("I can also bowl");
    }
}
