package cn.guice.demo.test;

/**
 * Created by Administrator on 2017/5/26.
 */
public class Main {
    public static void main(String[] args) {

        Named named = Names.named("a");
        System.out.println(named.value());




    }
}
