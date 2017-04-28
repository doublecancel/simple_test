package test1;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by Administrator on 2017/4/27.
 */
public class Consumer {

    public static void main(String[] args) {
        Channel channel = null;
        Connection connection = null;
        try {
            connection = Utils.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare("firstQueue", true, false, false, null);
            com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("consumer receive:" + message);
                }

                @Override
                public void handleConsumeOk(String consumerTag) {
                    System.out.println("consumer,handleConsumeOk");
                    super.handleConsumeOk(consumerTag);
                }

                @Override
                public void handleRecoverOk(String consumerTag) {
                    System.out.println("consumer,handleRecoverOk");
                    super.handleRecoverOk(consumerTag);
                }
            };

            channel.basicConsume("firstQueue", false, consumer);
//            channel.basicAck(10L, true);
        } catch (Exception e) {

        } finally {
            try {
//                Utils.close(channel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }






    }
}
