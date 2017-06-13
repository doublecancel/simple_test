package cn.guice.demo.service;

/**
 * Created by Administrator on 2017/5/26.
 */
public class BadPlayer implements Player {
    @Override
    public void bat() {
        System.out.println("I think i can face the ball");
    }

    @Override
    public void bowl() {
        System.out.println("I dont know bowling");
    }
}
