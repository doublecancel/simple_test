package com.easyweb.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/5/24.
 */
public class User extends ModelBase {


    @NotNull(message = "id不能为空", groups = {First.class})
    private Long id;

    @Length(min = 1, max = 5, message = "用户姓名长度不符合条件")
    private String username;

    @NotNull(message = "密码不能为空", groups = {First.class})
    private String password;

    @NotNull(message = "确认密码不能为空", groups = {Second.class})
    private String password2;


    private Boolean valid;




}
