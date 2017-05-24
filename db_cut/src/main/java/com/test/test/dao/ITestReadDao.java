package com.test.test.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Administrator on 2017/4/13.
 */
@Mapper
public interface ITestReadDao {
    String findDomainById(Long id);
}
