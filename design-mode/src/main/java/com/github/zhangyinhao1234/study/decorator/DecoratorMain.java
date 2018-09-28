package com.github.zhangyinhao1234.study.decorator;

public class DecoratorMain {

    public static void main(String[] args) {
        DecoratorHelloImpl decoratorHelloImpl = new DecoratorHelloImpl();
        BeforeDecoratorHello beforeDecoratorHello = new BeforeDecoratorHello(
                new AfterDecoratorHello(decoratorHelloImpl));
        beforeDecoratorHello.sayHello("zhang san ");

    }
}
