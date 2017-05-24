package com.test.test.reflect;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/4/21.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Resource {
    String value();
}
