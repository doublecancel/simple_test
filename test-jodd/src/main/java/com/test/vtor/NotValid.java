package com.test.vtor;

import jodd.vtor.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/5/24.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(NotValidConstraint.class)
public @interface NotValid {
    String name() default "";

    /**
     * Profiles.
     */
    String[] profiles() default {};

    /**
     * Severity.
     */
    int severity() default 0;

    /**
     * Message.
     */
    String message() default "jodd.vtor.constraint.NotValid";


}
