package com.test.vtor;

import jodd.vtor.Violation;
import jodd.vtor.Vtor;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
public class TestVtor {


    public static void main(String[] args) {

        User user = new User("", "lxl", "");

        List<Violation> violations =  Vtor.create().validate(user);
        if (violations!= null && violations.size() > 0) {
            violations.forEach((a) -> {
                System.out.println(a.getCheck().getMessage());
            });
        } else {
            System.out.println("验证成功！");
        }


    }





}
