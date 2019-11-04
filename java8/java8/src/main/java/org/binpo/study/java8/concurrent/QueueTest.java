package org.binpo.study.java8.concurrent;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author yinhao.zhang
 * @Date 2019/11/4 13:45
 * 常规队列使用
 **/
public class QueueTest {


    public static void main(String[] args) throws IOException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(20);

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0 ;i<4;i++){
            service.submit(new WriteThread(queue));
        }
        for(int i =0;i<2;i++){
            service.submit(new PrintThread(queue));
        }

    }


    public static class WriteThread implements Runnable {
        private ArrayBlockingQueue<String> queue;

        public WriteThread(ArrayBlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true){
                    Thread.sleep(500);
                    String s = "现在时间是：" + new Date();
                    queue.put(s);
                    System.out.println("放入数据："+s);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static class PrintThread implements Runnable {
        private ArrayBlockingQueue<String> queue;

        public PrintThread(ArrayBlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while(true){
                    String take = queue.take();
                    System.out.println("读取数据：" + take);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
