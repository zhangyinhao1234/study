package com.github.zhangyinhao1234.study.decorator;

public class BeforeDecoratorHello extends AbstractDecoratorHello {

    public BeforeDecoratorHello(DecoratorHello decoratorHello) {
        super(decoratorHello);
    }

    @Override
    public void sayHello(String hello) {
        before();
        super.sayHello(hello);
    }

    private void before() {
        System.out.println("......before.......");
    }
}
