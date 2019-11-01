package org.binpo.study.java8.concurrent;

import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * @Author yinhao.zhang
 * @Date 2019/11/1 17:15
 * 基于型号量的继续并发线程数量限制
 **/
public class SemaphoreTest {


    public static void main(String[] args) {

        int workerNo = 10;
        Semaphore semaphore = new Semaphore(5);
        for (int i=0;i<workerNo;i++){
            new WorkerThread(semaphore,i).start();
        }

    }

    static class WorkerThread extends Thread {
        Semaphore semaphore;int i;
        public WorkerThread(Semaphore semaphore,int i) {
            this.semaphore= semaphore;
            this.i = i;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("线程："+i+"开始工作......"+new Date());
                if (i % 2 == 0){
                    Thread.sleep(3000);
                }else{
                    Thread.sleep(4000);
                }
                System.out.println("线程："+i+"工作结束......"+new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
            System.out.println("需要控制同时执行线程数量结束，线程"+i+"可以执行其他事情了"+new Date());
        }
    }
}
