package com.test.test.reflect.testGeneric;

/**
 * Created by Administrator on 2017/4/21.
 */
public class Pair<T> {

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
