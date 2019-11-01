package org.binpo.study.java8.concurrent;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author yinhao.zhang
 * @Date 2019/11/1 16:17
 * 在各自线程中，等待所有线程的相关任务执行完成后，在各自线程继续执行任务
 **/
public class CyclicBarrierTest {


    public static void main(String[] args) {
        int n = 4;
        CyclicBarrier test = new CyclicBarrier(n);
        for(int i =0;i<n;i++){
            new PrintThread(test).start();
        }

    }


    static class PrintThread extends Thread{
        CyclicBarrier test;
        public PrintThread(CyclicBarrier test) {
            this.test = test;
        }

        @Override
        public void run() {
            System.out.println("开始准备工作"+new Date());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("准备工作结束。。。。。"+new Date());
            try {
                test.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("所有线程准备工作完成，继续执行相关工作.......");

        }
    }



}

