import com.atomikos.MyApplication;
import com.atomikos.dao.LogDao;
import com.atomikos.dao.UserDao;
import com.atomikos.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

/**
 * Created by Administrator on 2017/4/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MyApplication.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
public class TestAtomikos {


    @Autowired
    LogDao logDao;
    @Autowired
    UserDao userDao;


    @Test
//    @Transactional(value = "txManager1")//dataSourceA事务控制
    @Transactional(value = "jtaTransactionManager")//atomikos事务控制
    public void test(){

        IntStream.range(1, 100).forEach((a) -> {
            User u = User.builder().id(1L).name("lxl0" + a).age(10).build();
            logDao.save(u);
        });

        IntStream.range(1, 100).forEach((a) -> {
            User u = User.builder().id(1L).name("lxl0" + a).age(10).build();
            userDao.save(u);
        });

        User u = User.builder().id(1L).name("lxl050").age(10).build();
        userDao.save(u);

    }




}
