package test.reflect;

import java.lang.annotation.*;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/4/21.
 */
public class Java8Test {
    public static void main(String[] args) {
        Class<B> b = B.class;
        Method[] methods = b.getMethods();
        for (Method method : methods) {
            Annotation resource = method.getAnnotation(Resource.class);
            if (resource != null) {
                System.out.println(method);
            }
        }
    }
}

class A<T extends Object> {

    void print(T t) {
    }
}

class B extends A<String> {
    @Resource("aaa")
    public void print(String s) {

    }
}
