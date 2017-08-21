package com.test.test.map;

import com.google.common.collect.Lists;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2017/8/18.
 */
public class java8 {

    public static java8 create(){
        return new java8();
    }

    private AtomicInteger ai = new AtomicInteger();

    public static void main(String[] args) throws Exception {

//        create().testAtomicInteger();
//        testCompletableFuture();
//        testHashMap();

//        testFiles();

        init();
        faster();

    }



    public void testAtomicInteger(){

        ExecutorService pool =  Executors.newFixedThreadPool(10);
        IntStream.range(1, 100).forEach(a -> pool.submit(() -> {
//            System.out.println(ai.incrementAndGet());
        }));

        IntStream.range(1, 3).forEach(a -> {
            System.out.println(ai.accumulateAndGet(2, (x, y) -> x + y));//相加的基数是x
        });

        IntStream.range(1, 3).forEach(a -> {
            System.out.println(ai.updateAndGet(x -> x + 10));
        });

    }

    public static void testCompletableFuture(){

        CompletableFuture future = new CompletableFuture();
        future.complete("42");

        future.thenAccept((a) -> {
            System.out.println("then accept :" + a);
        }).thenAccept((a) -> {
            System.out.println("then accept ... :" + a);
        });
        future = CompletableFuture.supplyAsync(() -> {
            return "begin";
        }).thenApply(a -> {
            return "begin then";
        }).thenAccept(a -> {
            System.out.println(a + " accept");
        });

    }


    public static void testHashMap(){
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("a", "pppppp");
        map.put("b", "2");
        map.put("c", "3");

        String str = map.reduce(1, (a, b) -> a + "$" + b, (x, y) -> x + "#" + y);//拼接map key集合和value集合

        System.out.println(str);

        map.forEach((k, v) -> {
            System.out.println("k:" + k + ",v:" + v);
        });

        String s = map.search(2, (a, b) -> {
            if(a.equals("b")){
                return "result";
            }
            return null;
        });
        System.out.println("s:" + s);

        String v = map.searchValues(1, value -> {
            if(value.equals("3")){
                return value;
            }
            return null;
        });
        System.out.println("v : " + v);

    }


    public static void testExecutors() throws Exception{
        ExecutorService pool = Executors.newWorkStealingPool();

        List<Callable<String>> works = Lists.newArrayList(
                () -> "a",
                () -> "b",
                () -> "c",
                () -> "d"
        );

        pool.invokeAll(works).stream().map(f -> {
            try {
                return f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }).forEach(System.out::println);
    }


    public static void testFiles() throws Exception{

        //读取文件内容
        Path path = Paths.get("D:\\workspace\\github\\simple_test\\test_java\\src\\main\\");
//        Files.getFileStore(path);
//        BufferedReader reader = Files.newBufferedReader(path);
//        reader.lines().forEach(a -> {
//            System.out.println(a);
//        });
//
//        Stream<String> lines = Files.lines(path);//转换为stream

        List<Path> list = Files.walk(path).collect(Collectors.toList());
//        list.forEach(a -> {
//            System.out.println(a.getFileName());
//        });

        Files.find(path, 15, (p, attr) -> {
            System.out.println(p.getFileName());
            return p.getFileName().endsWith(".java");
        }).collect(Collectors.toList()).forEach(a -> {
            System.out.println(a);
        });

    }

    public static void testString(){
        "abcdef".chars().distinct().forEach(a -> {
            System.out.println(a);
        });

        String ss = String.join("a", "1", "2");

    }

    public void testStampedLock(){}

    public static void testJs(){
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    }



    private static Map<String, String> map = new HashMap<>(1 << 20);
    private static List<String> data = new ArrayList<>(1 << 20);

    public static void init(){
        IntStream.range(0, 1 << 20).forEach(a -> {
            data.add("data" + a);
        });
    }

    public static void faster(){
        System.out.println("当前list的数量：" + data.size());
        Long start = System.currentTimeMillis();

        data.stream()
                .filter(a -> {
            return a.endsWith("0");
        }).map(a -> a + "map")
                .collect(Collectors.toList());

        System.out.println("stream执行时间：" + (System.currentTimeMillis() - start));
        Long end = System.currentTimeMillis();
        List<String> oo = new ArrayList<>(data.size());
        int size = data.size();
        for(int a = 0; a < size; a++){
            String temp = data.get(a);
            if(temp.endsWith("0")){
                temp = temp + "map";
                oo.add(temp);
            }
        }
        System.out.println("for循环执行时间：" + (System.currentTimeMillis() - end));


    }




}
