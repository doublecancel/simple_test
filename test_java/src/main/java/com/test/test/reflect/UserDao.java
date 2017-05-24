package com.test.test.reflect;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */
public class UserDao implements BaseDao<UserEntity, Long> {
    @Resource("userDao:save")
    @Override
    public void save(UserEntity userEntity) {

    }

    @Override
    public UserEntity getById(Long id) {
        return null;
    }

    @Override
    public List<UserEntity> list() {
        return null;
    }
}
