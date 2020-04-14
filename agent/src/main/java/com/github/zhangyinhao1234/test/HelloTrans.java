package com.github.zhangyinhao1234.test;

public class HelloTrans {

    public static void main(String[] args) {
        HelloTrans HelloTrans = new HelloTrans();
        HelloTrans.hi();

        new Foo().hi();
    }

    public void hi(){
        System.out.println("hi.........HelloTrans");
    }
}
