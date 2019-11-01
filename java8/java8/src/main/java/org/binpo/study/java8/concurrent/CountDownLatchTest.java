package org.binpo.study.java8.concurrent;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @Author yinhao.zhang
 * @Date 2019/11/1 11:54
 * 等待其他线程完成后继续执行主线程   和join 类似
 * join 需要等线程都完成后才能继续执行主线程，但是countdownlatch只需要等线程中部分工作完成就可以进行标记继续执行主线程代码
 **/
public class CountDownLatchTest {

    public static void main(String[] args) {

        CountDownLatch downLatch = new CountDownLatch(2);

        new Thread(){
            @Override
            public void run(){
                System.out.println("thread 1 start ....");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 1 stop ....");
                downLatch.countDown();


                System.out.println("thread 1 start part2 work ...." + new Date());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 1 part2 work stop ...." + new Date());

            }
        }.start();


        new Thread(){
            @Override
            public void run(){
                System.out.println("thread 2 start ....");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 2 stop ....");
                downLatch.countDown();


                System.out.println("thread 2 start part2 work ...." + new Date());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 2 part2 work stop ...." + new Date());

            }
        }.start();

        System.out.println("wait two child thread stop"+ new Date());
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("two child thread stop" + new Date());

    }
}
