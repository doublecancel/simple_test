package com.rx.java.demo;

import io.reactivex.Flowable;

import java.util.concurrent.CompletableFuture;

/**
 * Created by Administrator on 2017/6/11 0011.
 */
public class Day01 {

    public static void main(String[] args) {


        Flowable.just("hello world").subscribe(System.out::println);



        testCompleteFture();


    }






    public static void testCompleteFture (){

        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            return "aaa";
        }).thenApply((a) -> {
            System.out.println("a:" + a);
            return a;
        }).thenAccept((b) -> {
            System.out.println("b:" + b);
        }).exceptionally((e) -> {
            System.out.println(e.getCause());
            return null;
        });

    }


}
