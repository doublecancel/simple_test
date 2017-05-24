package com.easyweb.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/5/24.
 */
@Component
public class LisiListener implements ApplicationListener<ApplicationEvent> {



    @Async("")
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContentEvent){


            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("thred:" + Thread.currentThread().getName());

            System.out.println("listener......................." + event.getSource());


        }
    }
}
