package com.test.test.service;


import com.test.test.domain.TestDomain;

/**
 * Created by hls
 * time:2017-02-16 09:21:15
 */
public interface ITestService  {
//    @DynamicDataSource(DbContextHolder.SLAVE)
    String getById(Long id);

    void save(TestDomain domain);

    void delete(Long id);

    void update(TestDomain domain);

}