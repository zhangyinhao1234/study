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
        CyclicBarrier test = new CyclicBarrier(n, new Runnable() {
            @Override
            public void run() {
                //所有子线程的准备工作完成后会调用这个方法
                System.out.println("子线程处理完工作后可以执行一些额外的工作。。。。。");
            }
        });
        for(int i =0;i<n;i++){
            new PrintThread(test,i).start();
        }

    }


    static class PrintThread extends Thread{
        CyclicBarrier test;
        int i;
        public PrintThread(CyclicBarrier test,int i) {
            this.test = test;
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println("Thread: "+i+" 开始准备工作"+new Date());
            try {
                if (i == 3){
                    Thread.sleep(6000);
                }else{
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread: "+i+" 准备工作结束。。。。。"+new Date());
            try {
                test.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("Thread: "+i+ " 所有线程准备工作完成，继续执行相关工作.......");

        }
    }



}
