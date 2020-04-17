package com.github.zhangyinhao1234.test;

import com.github.zhangyinhao1234.asm.ShadowSocks;

public class HelloTrans {

    public static void main(String[] args) {
        HelloTrans HelloTrans = new HelloTrans();

        HelloTrans.hi();

        new Foo().hi();

//        new RunTest().run();


    }


    public void hi() {
        System.out.println("hi.........HelloTrans");
    }


}


class RunTest implements Runnable {

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                hi();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void hi() {
        System.out.println("RunTest.......");
    }
}