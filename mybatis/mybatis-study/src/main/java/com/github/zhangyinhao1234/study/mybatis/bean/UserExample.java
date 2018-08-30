package com.github.zhangyinhao1234.study.mybatis.bean;

import java.io.Serializable;

public class UserExample implements Serializable{

    /**
     * TODO 字段的作用/说明
     */
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

}
