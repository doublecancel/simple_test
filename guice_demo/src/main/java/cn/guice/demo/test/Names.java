package cn.guice.demo.test;

/**
 * Created by Administrator on 2017/5/26.
 */
public final class Names {

    private Names(){}


    public static Named named(String value){
        return new NameImpl(value);
    }


}
