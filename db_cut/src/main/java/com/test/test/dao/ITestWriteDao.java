package com.test.test.dao;

import org.apache.ibatis.annotations.Mapper;
import com.test.test.domain.TestDomain;

/**
 * Created by Administrator on 2017/4/13.
 */
@Mapper
public interface ITestWriteDao {
    void updateDomain(TestDomain domain);
}
