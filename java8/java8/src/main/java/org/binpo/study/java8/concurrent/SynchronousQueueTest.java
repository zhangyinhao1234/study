package org.binpo.study.java8.concurrent;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author yinhao.zhang
 * @Date 2019/11/5 10:31
 * 在线程池中提交任务对象越多且处于阻塞中，那么会占用较大的空间，来不及消费可能会造成内容溢出
 **/
public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        SynchronousQueue<String> queue = new SynchronousQueue<>();
//        new Thread(new WriteThread(queue)).start();
//

        TimeUnit.SECONDS.sleep(20);

        for(int i = 0;i<50;i++){
            executorService.submit(new WriteThread(queue));
        }


        TimeUnit.SECONDS.sleep(10);
        for(int i =0;i<3;i++){
            new Thread(new ReadThread(queue,"0")).start();
        }


    }

    static class WriteThread implements Runnable{
        private SynchronousQueue<String> queue;
        private byte[] b = new byte[1024*1024]; //1M

        public WriteThread(SynchronousQueue queue) {
            this.queue = queue;
        }
        @Override
        public void run() {

//            while (true){
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
//            }
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
                    if(!threadName.equals("0")){
                        TimeUnit.SECONDS.sleep(2);
                    }
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
