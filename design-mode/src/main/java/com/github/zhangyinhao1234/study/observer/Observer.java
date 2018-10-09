package com.github.zhangyinhao1234.study.observer;

/**
 * @author 【张殷豪】
 * 观察者，发布定于模式中的订阅者
 * 定义了一个update()方法，当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调。
 * Date 2018/10/9 22:18
 */
public interface Observer {
    void update(String message);
}
