import dao.TestDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2017/6/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TestUser {


    @Autowired
    TestDao dao;

    @Test
    public void test(){
        dao.findByid(1L);
//        dao.findById();
    }

}
