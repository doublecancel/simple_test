package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Administrator on 2017/6/8.
 */
@Component
public class TestDao {


    @Autowired
    JdbcTemplate template;


    public void findByid(Long id) {
        System.out.println(template.queryForObject("select name from user where id = 1", null, java.lang.String.class));
        System.out.println(template.queryForObject("select name from user where id = 2", null, java.lang.String.class));
        System.out.println(template.queryForObject("select name from user where id = 3", null, java.lang.String.class));
    }


    @Autowired
    DataSource dataSource;


//    @Resource
//    SpringShardingDataSource shardingDataSource;
//
//
    public void findById() {
        String sql = "select name from user where id = 1";
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
