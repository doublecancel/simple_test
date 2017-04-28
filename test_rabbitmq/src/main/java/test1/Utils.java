package test1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by Administrator on 2017/4/28.
 */
public class Utils {

    public static Connection newConnection(){
        Connection connection = null;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.235.131");
        factory.setPort(5672);
        factory.setUsername("root");
        factory.setPassword("root");
        factory.setVirtualHost("test_vhosts");
        try {
            connection = factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Channel channel) throws Exception {
        channel.close();
    }
    public static void close(Connection channel) throws Exception {
        channel.close();
    }


}
