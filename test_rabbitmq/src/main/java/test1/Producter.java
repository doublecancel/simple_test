package test1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * Created by Administrator on 2017/4/27.
 */
public class Producter {

    public static void main(String[] args) {
        Channel channel = null;
        Connection connection = null;
        try {
            connection = Utils.newConnection();
            channel = connection.createChannel();
            channel.addConfirmListener(new ConfirmListener(){

                @Override
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("handleAck..........");
                }

                @Override
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("handleNack..........");
                }
            });
            Thread.sleep(1000);
            String message = "message body";
            channel.basicPublish("", "firstQueue", null, message.getBytes());
            System.out.println("send message is:" + message);
        }catch (Exception e){

        }finally {
            try {
//                Utils.close(channel);
//                Utils.close(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }
}
