package org.binpo.study.java8.concurrent;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author yinhao.zhang
 * @Date 2019/11/8 11:12
 * 通过内存屏蔽实现内存可见性，B线程对A线程中的 volatile 数据可见，通过内存屏蔽将  volatile 强制刷新到主内存，其他线程读取因为在读之前设置了内存屏蔽
 * 强制要求去主内存读取数据，实现数据之间的内存可见
 * 不保证原子性
 **/
public class VolatileTest {

    private volatile int count = 0;
    Integer c = new Integer(0);

    private volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException, IOException {

        VolatileTest test = new VolatileTest();
//        new Thread(new WriteThread(test)).start();
//        new Thread(new ReadThread(test, "1")).start();
//        new Thread(new ReadThread(test,"2")).start();


        // Integer 是地址地址传递，但是在Integer 中value属性是final，所以每次进行加减擦操作的时候其实是创建了一个新的Integer，
        // 而方法上入参的Integer是不可变的，所以他的生命周期也只在方法内部，出了这个方法，就会被销毁，而和原来的Integer也没有关系了
//        new Thread(new WriteThread2(test.c)).start();
//        new Thread(new ReadThread2(test.c)).start();
//
//        TimeUnit.SECONDS.sleep(3000);
//        System.out.println("c:" + test.c);

        new Thread(new StopThread(test,"1")).start();
        new Thread(new StopThread(test,"2")).start();
        new Thread(new StopThread(test,"3")).start();
        new Thread(new StopThread(test,"4")).start();
        new Thread(new StopThread(test,"5")).start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("now:"+System.nanoTime());
        test.stop = true;

    }

    static class WriteThread implements Runnable {
        VolatileTest test;

        public WriteThread(VolatileTest test) {
            this.test = test;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.count++;
            }
        }
    }


    static class WriteThread2 implements Runnable {
        volatile Integer c;

        public WriteThread2(Integer c) {
            this.c = c;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                c++;
            }
        }
    }


    static class ReadThread implements Runnable {
        VolatileTest test;
        String strategy;

        public ReadThread(VolatileTest test, String strategy) {
            this.test = test;
            this.strategy = strategy;
        }

        @Override
        public void run() {
            if ("2".equals(strategy)) {
                System.out.println("策略 2   数据：count：" + test.count);
                while (true) {
                    System.out.println("通过内存可见性处理得到数据为  " + test.count);
                }
            } else {
                while (true) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("count:" + test.count);
                }
            }
        }
    }


    static class ReadThread2 implements Runnable {
        volatile Integer c;

        public ReadThread2(Integer c) {
            this.c = c;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("count:" + c);
            }
        }
    }

    static class StopThread implements Runnable{
        VolatileTest test;
        String threadName;
        public StopThread(VolatileTest test, String threadName) {
            this.test = test;
            this.threadName = threadName;
        }

        @Override
        public void run() {
            while (!test.stop){
                System.out.println("ThreadName:"+this.threadName+"   标记未变化:"+System.nanoTime());
            }
        }
    }






}
