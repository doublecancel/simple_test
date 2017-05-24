/*
 * 所属项目: hlms.itms
 * 创建人: mt
 * 创建时间: 2016-07-04 19:48
 */

package com.test.test.dao;

import jodd.util.StringUtil;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import com.test.test.config.routingDataSource.DbContextHolder;

import java.sql.Connection;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Created by mt on 2016-07-04 19:48.
 *
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class MyBatisInterceptor implements Interceptor {

    private static final Pattern SQL_SELECT_PATTERN = Pattern.compile("(?is)^\\s*SELECT.*$");

    @Override
    public Object intercept(Invocation inv) throws Throwable {
        StatementHandler target = (StatementHandler) inv.getTarget();
        String sql = target.getBoundSql().getSql();

        if (!StringUtil.isBlank(sql)) {
            if (SQL_SELECT_PATTERN.matcher(sql).matches()) {
                DbContextHolder.slave();
            } else {
                DbContextHolder.master();
            }
        }
        return inv.proceed();
    }



    @Override
    public Object plugin(Object target) {
        if (target instanceof ResultSetHandler) {
            ResultSetHandler handler = ((ResultSetHandler) target);
        }
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
