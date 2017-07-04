package com.test.test.annotation;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/5/26.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Method m = Main.class.getDeclaredMethod("test", new Class[]{Integer.class});
        Annotation[][] as = m.getParameterAnnotations();
        for(int a = 0; a < as.length; a++){

            for(int b = 0; b < as[a].length; b++){

                System.out.println(as[a][b].annotationType().getName());

            }
        }


    }



    public void test(@Not0 Integer a){



    }






}
