package spring.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/4/28.
 */
@Configuration
public class RabbitConfig {


    @Bean
    public ConnectionFactory defaultConnectionFactory() {
        CachingConnectionFactory cf = new CachingConnectionFactory();
        cf.setAddresses("192.168.235.131");
        cf.setUsername("root");
        cf.setPassword("root");
        cf.setVirtualHost("");
        return cf;
    }


}
