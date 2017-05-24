package com.test.test.oom;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by Administrator on 2017/5/22.
 */
public class Test {


//    public static void main(String[] args) {
//
//        Vector v = new Vector();
//        for (int i = 0; i < 25; i++) {
//            v.add(new byte[1 * 1024 * 1024]);
//        }
//    }


    public static void main(String[] args) {
//        Vector v = new Vector(10);
//        while (true) {
//            Object o = new Object();
//            v.add(o);
//            o = null;
//        }

        LongAdder adder = new LongAdder();

        AtomicLong atomicLong = new AtomicLong(1);


    }


}
