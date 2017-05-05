package spring.simple;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.ReceiveAndReplyCallback;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.stream.IntStream;

/**
 * Created by Administrator on 2017/5/2.
 */
public class Test1 {

    public static void main(String[] args) {

        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("root");
        factory.setHost("192.168.235.131");
        factory.setPort(5672);
        RabbitAdmin admin = new RabbitAdmin(factory);
        admin.declareQueue(new Queue("test"));//创建队列
        AmqpTemplate template = new RabbitTemplate(factory);
        //发送到rabbitmq,100条
        IntStream.range(1, 100).forEach((a) -> {
            template.convertAndSend("test" , "hello" + a);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //接受消息
        template.receiveAndReply("test", new ReceiveAndReplyCallback<String, String>() {
            @Override
            public String handle(String payload) {

                System.out.println("payload:" + payload);

                return null;
            }
        });
    }

}
