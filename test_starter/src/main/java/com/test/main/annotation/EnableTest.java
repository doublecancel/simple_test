package com.test.main.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/4/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(EnableTestConfigure.class)
@Documented
public @interface EnableTest {
}
