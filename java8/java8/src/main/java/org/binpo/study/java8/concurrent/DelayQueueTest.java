package org.binpo.study.java8.concurrent;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author yinhao.zhang
 * @Date 2019/11/4 16:11
 *
 * 延迟消息队列，在指定的延迟时间后才能读取到相关消息
 **/
public class DelayQueueTest {


    public static void main(String[] args) {

        DelayQueue<Message> queue = new DelayQueue<>();
        queue.add(new Message(1,"消息1",3000));
        queue.add(new Message(1,"消息1",5000));
        System.out.println("现在时间："+new Date());


        new Thread(new ReadThread(queue)).start();

    }

    static class ReadThread implements Runnable {

        private DelayQueue<Message> queue;

        public ReadThread(DelayQueue<Message> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Message msg = queue.take();
                    System.out.println("now:" + new Date() + " info:"+msg.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static class Message implements Delayed {
        int id;
        String msg;
        long expertTime;

        public Message(int id, String msg, long expertTime) {
            this.id = id;
            this.msg = msg;
            this.expertTime = TimeUnit.NANOSECONDS.convert(expertTime,TimeUnit.MILLISECONDS) + System.nanoTime();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expertTime - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            Message msg = (Message) o;
            return this.id < msg.id ? -1 : 1;
        }

        @Override
        public String toString() {
            return "{id:" + id + ",msg:" + msg + "}";
        }
    }

}
