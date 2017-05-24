package com.test.test.domain;


/**
 * Created by hls
 * time:2017-03-15 10:42:57
 */

public class TestDomainBase {

    protected Long id;  //无描述
    protected String name;  //无描述
    protected Integer gender;  //无描述
    protected String nickname;  //无描述
    protected String ip;  //无描述

    //region [gets]
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getGender() {
        return gender;
    }

    public String getNickname() {
        return nickname;
    }

    public String getIp() {
        return ip;
    }

    //endregion
//region [sets]
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
//endregion


    @Override
    public String toString() {
        return "TestDomainBase{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", nickname='" + nickname + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
