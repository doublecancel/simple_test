package com.typetoken;

/**
 * Created by Administrator on 2017/6/12.
 */
public class Children extends Parent implements IChildren {
    @NotCheck
    @Override
    public void test() {
        System.out.println("children _ test");
//        parent_test();
    }
}
