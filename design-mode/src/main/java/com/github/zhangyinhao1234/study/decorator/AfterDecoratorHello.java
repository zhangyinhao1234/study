package com.github.zhangyinhao1234.study.decorator;

public class AfterDecoratorHello extends AbstractDecoratorHello {

    public AfterDecoratorHello(DecoratorHello decoratorHello) {
        super(decoratorHello);
    }

    @Override
    public void sayHello(String hello) {
        super.sayHello(hello);
        after();
    }

    private void after() {
        System.out.println("......after......");
    }

}
