package cn.guice.demo.test;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/5/26.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Named {
    String value();
}
