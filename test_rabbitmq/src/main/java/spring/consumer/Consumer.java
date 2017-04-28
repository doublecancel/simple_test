package spring.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/4/28.
 */
@Component
public class Consumer {


    @RabbitListener(queues = "", admin = "")
    public void listener(String result){
        System.out.println(result);
    }








}
