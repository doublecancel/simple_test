package com.test.test.reflect;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */
public interface BaseDao<T extends EntityBase, PK extends Serializable> {

//    @Resource("BaseDao save")
    void save(T t);

    T getById(PK id);

    List<T> list();


}
