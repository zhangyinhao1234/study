package org.binpo.study.java8.concurrent;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author yinhao.zhang
 * @Date 2019/11/5 10:31
 **/
public class SynchronousQueueTest {

    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(new WriteThread(queue)).start();

        for(int i =0;i<3;i++){
            new Thread(new ReadThread(queue,i+"")).start();
        }

    }

    static class WriteThread implements Runnable{
        private SynchronousQueue<String> queue;

        public WriteThread(SynchronousQueue queue) {
            this.queue = queue;
        }
        @Override
        public void run() {

            while (true){
                String anInt = UUID.randomUUID().toString();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    String ss = "存入消息："+anInt + new Date();
                    System.out.println(ss);
                    queue.put(ss);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static class ReadThread implements Runnable{
        private SynchronousQueue<String> queue;

        private String threadName;

        public ReadThread(SynchronousQueue<String> queue, String threadName) {
            this.queue = queue;
            this.threadName = threadName;
        }

        @Override
        public void run() {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    String take = queue.take();
                    String ss = "线程"+this.threadName+"  :  读取消息："+ take ;
                    System.out.println(ss);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
