package com.github.zhangyinhao1234.study.springboot.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.github.zhangyinhao1234.study.springboot.base.IdEntity;

@Entity
@Table(name = "tcm_test_user")
public class UserExample extends IdEntity {

    @Column(name = "userName")
    private String userName;

    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
