package com.github.zhangyinhao1234.study;

public class ThreadTest {

    public static ThreadLocal<String> threadLoacl = new InheritableThreadLocal<String>();

    public static void main(String[] args) throws InterruptedException {
        threadLoacl.set("main...");

        System.out.println("main.start");
        new NewThread().start();

        Thread.sleep(2000);
        System.out.println("main.run");

        new NewThread().start();

        Thread.sleep(2000);
    }




}
class NewThread extends Thread{

    @Override
    public void run(){
        System.out.println("child thread "+ThreadTest.threadLoacl.get());
    }
}
