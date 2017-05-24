package com.test.test.reflect.testGeneric;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/21.
 */
public class Main {


    public static void main(String[] args) {
        DateInter dateInter = new DateInter();
        dateInter.setValue(new Date());

        Serializable a = pick("a", new ArrayList<String>());
        System.out.println(a);


        Test test = new Test();



    }


    public static <T> T pick(T a, T b){
        return b;
    }
}
