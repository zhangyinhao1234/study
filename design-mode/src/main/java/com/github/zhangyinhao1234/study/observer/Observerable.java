package com.github.zhangyinhao1234.study.observer;

/**
 * @author 【张殷豪】
 * 抽象被观察者接口
 * 声明了添加、删除、通知观察者方法
 * Date 2018/10/9 22:23
 */
public interface Observerable {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObserver();

}
