package com.test.test.command;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2017/8/17.
 */
public class Main {

    public static void main(String[] args) {

        TV tv = new TV();

        OpenCommand openCommand = new OpenCommand(tv);
        CloseCommand closeCommand = new CloseCommand(tv);
        ChangeCommand changeCommand = new ChangeCommand(tv);

        Control control = new Control(openCommand, closeCommand, changeCommand);

//        control.open();
//
//        control.close();
//
//        control.change(1);


        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(5),
                new ThreadPoolExecutor.AbortPolicy());

        System.out.println("queueSize : " + executor.getQueue().size());

        IntStream.range(1, 13).forEach(a -> {
            executor.execute(() -> {
                System.out.println("进入新的线程");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程结束");
            });
            System.out.println("------------------------------------------------" + a);
            System.out.println("queueSize : " + executor.getQueue().size());
            System.out.println("activeCount : " + executor.getActiveCount());
        });

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("============================================================");
        System.out.println("queueSize : " + executor.getQueue().size());

    }


}
