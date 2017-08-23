package com.typetoken;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/12.
 */
public class Main {


    public static void main(String[] args) {



        Method[] ms = Children.class.getMethods();//获取父类和子类中所有的公共方法
        Method[] ms1 = Children.class.getDeclaredMethods();//获取子类中所有的方法

//        Arrays.stream(ms).forEach((a) -> {
//            System.out.println(a.getName());
//        });

        for(Method m : ms){
            if(m.isAnnotationPresent(NotCheck.class)){
                System.out.println(m.getName());
            }
        }

        //如何获取父类中的非公有方法
        Set<?> set = TypeToken.of(Children.class).getTypes().rawTypes();//获取了子类和所有父类
        set.forEach((a) -> {
            System.out.println(a);
        });

        Class<?>[] cs = Children.class.getClasses();
        Arrays.stream(cs).forEach(System.out::println);


    }


}
