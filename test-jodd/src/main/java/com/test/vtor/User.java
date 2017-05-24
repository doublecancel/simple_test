package com.test.vtor;

import jodd.vtor.constraint.Length;
import jodd.vtor.constraint.NotBlank;

/**
 * Created by Administrator on 2017/5/24.
 */
public class User {
    @NotBlank(message = "id不能为空")
    private String id;
    @Length(min = 1, max = 10, message = "姓名长度不正确")
    private String name;

    @NotValid(name = "", message = "密码不能为空")
    private String password;


    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
