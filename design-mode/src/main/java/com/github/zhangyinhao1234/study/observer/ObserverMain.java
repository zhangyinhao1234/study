package com.github.zhangyinhao1234.study.observer;

/**
 * @author 【张殷豪】
 * 观察者模式例子
 * Date 2018/10/9 22:38
 */
public class ObserverMain {
    public static void main(String[] args) {
        ObserverUser zhangsan = new ObserverUser("张三");
        ObserverUser lisi = new ObserverUser("李四");

        MessageService messageService = new MessageService();
        messageService.registerObserver(zhangsan);
        messageService.registerObserver(lisi);
        messageService.setInfomation("我要去旅游！！！");

        messageService.removeObserver(lisi);
        messageService.setInfomation("我旅游回来了。。。");

    }
}
