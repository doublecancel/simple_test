package cn.guice.demo;

import cn.guice.demo.annotation.MethodAop;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by Administrator on 2017/5/27.
 */
public class MethodBlocker implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("blocker");

        MethodAop aop = invocation.getMethod().getAnnotation(MethodAop.class);

        System.out.println(aop.name());

        return invocation.proceed();
    }
}
