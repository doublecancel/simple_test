package cn.guice.demo.test;

import java.lang.annotation.Annotation;

/**
 * Created by Administrator on 2017/5/26.
 */
public class NameImpl implements Named {

    private String value;

    public NameImpl(String value){
        this.value = value;
    }


    @Override
    public String value() {
        return this.value;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Named.class;
    }
}
