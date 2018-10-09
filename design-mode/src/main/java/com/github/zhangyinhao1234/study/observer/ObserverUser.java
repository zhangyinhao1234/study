package com.github.zhangyinhao1234.study.observer;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * @author 【张殷豪】
 * 观察者
 * Date 2018/10/9 22:34
 */

public class ObserverUser implements Observer {
    private String name;
    private String message;

    public ObserverUser(String name){
        this.name =name;
    }
    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    public void read() {
        System.out.println(name + " 收到推送消息： " + message);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
