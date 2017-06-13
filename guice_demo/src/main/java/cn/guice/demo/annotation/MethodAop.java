package cn.guice.demo.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/5/27.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface MethodAop {


    String name() default "";


}
