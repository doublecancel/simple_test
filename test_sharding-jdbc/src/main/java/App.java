import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2017/6/8.
 */
@SpringBootApplication(scanBasePackages = {"com.test.config", "dao"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }




    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }




}
