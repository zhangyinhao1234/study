package com.github.zhangyinhao1234.study.decorator;

public class DecoratorHelloImpl implements DecoratorHello {

    @Override
    public void sayHello(String hello) {
        System.out.println("hello....." + hello);
    }

}
