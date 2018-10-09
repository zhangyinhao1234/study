package com.github.zhangyinhao1234.study.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 【张殷豪】
 * 消息服务
 * 被观察者的实现
 * Date 2018/10/9 22:27
 */
public class MessageService implements Observerable {
    private List<Observer> list;
    private String message;

    public MessageService() {
        list = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        list.remove(o);
    }

    @Override
    public void notifyObserver() {
        list.forEach(o -> {
            o.update(message);
        });
    }
    public void setInfomation(String s) {
        this.message = s;
        System.out.println("更新消息： " + s);
        //消息更新，通知所有观察者
        notifyObserver();
    }
}
