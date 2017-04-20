package test.config.routingDataSource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import test.config.DynamicDataSource;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/4/13.
 */
@Aspect
@Component
@Order(0)
@Slf4j
public class ReadOnlyConnectionInterceptor {
    @Pointcut("@annotation(gg.test.config.DynamicDataSource)")
    public void DynamicDataSource(){}

    @Before("DynamicDataSource()")
    public void Before(JoinPoint joinPoint) {
        Class clazz = joinPoint.getTarget().getClass();
        MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
        String methodName = joinPointObject.getName();
        DynamicDataSource source = null;
        for(Method m : clazz.getDeclaredMethods()){
            if(methodName.equals(m.getName())){
                source = m.getAnnotation(DynamicDataSource.class);
                break;
            }
        }
        log.info(source.value() + ".......................................Before");
        DbContextHolder.setDataSourceType(source.value());
    }

}
