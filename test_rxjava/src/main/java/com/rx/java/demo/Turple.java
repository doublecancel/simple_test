package com.rx.java.demo;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/6/11 0011.
 */
public class Turple<A, B> {

    A a;
    B b;

    public Turple(A a, B b){
        this.a = a;
        this.b = b;
    }


    public static <A, B> Turple of(A a, B b){
        return new Turple(a, b);
    }

    public A _1(){
        return a;
    }
    public B _2(){
        return b;
    }

    public static void main(String[] args) {
        Turple turple = Turple.of(1, "a");
        System.out.println(turple._1());
        System.out.println(turple._2());


        System.out.println(new Gson().toJson(turple));
    }





}
