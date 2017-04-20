package test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.config.DynamicDataSource;
import test.config.routingDataSource.DbContextHolder;
import test.dao.ITestReadDao;
import test.dao.ITestWriteDao;
import test.domain.TestDomain;

/**
 * Created by Administrator on 2017/4/13.
 */
@Service
public class TestServiceImpl implements ITestService {


    @Autowired
    ITestReadDao readDao;

    @Autowired
    ITestWriteDao writeDao;

    @DynamicDataSource(DbContextHolder.SLAVE)
    public String getById(Long id) {
        return readDao.findDomainById(id) + "";
    }

    @Override
    public void save(TestDomain domain) {

    }

    @Override
    public void delete(Long id) {

    }
    @DynamicDataSource(DbContextHolder.MASTER)
    @Override
    public void update(TestDomain domain) {
        writeDao.updateDomain(domain);
    }
}


