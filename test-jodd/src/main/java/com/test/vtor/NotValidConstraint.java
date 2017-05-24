package com.test.vtor;

import com.google.common.base.Strings;
import jodd.vtor.ValidationConstraint;
import jodd.vtor.ValidationConstraintContext;

/**
 * Created by Administrator on 2017/5/24.
 */
public class NotValidConstraint implements ValidationConstraint<NotValid> {

    private String name;

    @Override
    public void configure(NotValid annotation) {
        name = annotation.name();
    }

    @Override
    public boolean isValid(ValidationConstraintContext vcc, Object value) {
        if(Strings.isNullOrEmpty(name)){
            return false;
        }
        return true;
    }
}
