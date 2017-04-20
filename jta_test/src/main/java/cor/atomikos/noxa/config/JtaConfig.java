package cor.atomikos.noxa.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.transaction.SystemException;

/**
 * Created by Administrator on 2017/4/17.
 */
@Configuration
public class JtaConfig {

    @Resource(name = "dataSourceA")
    DataSource dataSourceA;
    @Resource(name = "dataSourceB")
    DataSource dataSourceB;

    @Bean
    @Primary
    public AtomikosNonXADataSourceBean dataSourceA(){
        AtomikosNonXADataSourceBean bean = new AtomikosNonXADataSourceBean();//非XA事务
        bean.setUrl("jdbc:mysql://192.168.200.249:3306/test?useUnicode=true&amp;characterEncoding=utf-8&amp;serverTimezone=UTC&amp;useSSL=false");
        bean.setUser("root");
        bean.setPassword("root");
        bean.setDriverClassName("com.mysql.jdbc.Driver");
        bean.setUniqueResourceName("XA1DBMS");
        bean.setPoolSize(3);
        bean.setMinPoolSize(3);
        bean.setMaxPoolSize(5);
        return bean;
    }
    @Bean
    public AtomikosNonXADataSourceBean dataSourceB(){
        AtomikosNonXADataSourceBean bean = new AtomikosNonXADataSourceBean();//非XA事务
        bean.setUrl("jdbc:mysql://192.168.200.234:3306/test?useUnicode=true&amp;characterEncoding=utf-8&amp;serverTimezone=UTC&amp;useSSL=false");
        bean.setUser("root");
        bean.setPassword("root");
        bean.setDriverClassName("com.mysql.jdbc.Driver");
        bean.setUniqueResourceName("XA1DBMS");
        bean.setPoolSize(3);
        bean.setMinPoolSize(3);
        bean.setMaxPoolSize(5);
        return bean;
    }



    @Bean
    public UserTransactionImp userTransactionManager() throws SystemException {
        UserTransactionImp impl = new UserTransactionImp();
        impl.setTransactionTimeout(300);
        return impl;
    }
    @Bean
    public JtaTransactionManager jtaTransactionManager() throws SystemException {
        JtaTransactionManager manager = new JtaTransactionManager();
        manager.setUserTransaction(userTransactionManager());
        return manager;
    }

    @Bean
    @Primary
    public PlatformTransactionManager txManager1() throws SystemException {
        return new DataSourceTransactionManager(dataSourceA);
    }
    @Bean
    public PlatformTransactionManager txManager2() throws SystemException {
        return new DataSourceTransactionManager(dataSourceB);
    }


}
