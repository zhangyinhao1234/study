package com.github.zhangyinhao1234.study.decorator;

public abstract class AbstractDecoratorHello implements DecoratorHello {

    private DecoratorHello decoratorHello;

    public AbstractDecoratorHello(DecoratorHello decoratorHello) {
        this.decoratorHello = decoratorHello;
    }

    @Override
    public void sayHello(String hello) {
        this.decoratorHello.sayHello(hello);
    }

}
