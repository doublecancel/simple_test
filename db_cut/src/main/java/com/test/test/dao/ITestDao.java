package com.test.test.dao;

import org.apache.ibatis.annotations.Mapper;
import com.test.test.domain.TestDomain;

/**
 * Created by Administrator on 2017/4/14.
 */
@Mapper
public interface ITestDao {
    String findDomainById(Long id);
    void updateDomain(TestDomain domain);
}
