package org.binpo.study.java8.executor;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

/**
 * @Author yinhao.zhang
 * @Date 2019/10/31 17:19
 **/
public class DeaiLockTest {

    public StampedLock lock1 = new StampedLock();
    public StampedLock lock2 = new StampedLock();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        DeaiLockTest deaiLockTest = new DeaiLockTest();
        executorService.submit(new RunA(deaiLockTest));
        executorService.submit(new RunB(deaiLockTest));
    }






}

class RunA implements Runnable{
    DeaiLockTest test;
    public RunA(DeaiLockTest test) {
        this.test = test;
    }

    @Override
    public void run() {
        while (true){
            long readLock = test.lock1.writeLock();
            try {
                System.out.println("A...."+new Date());
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
//                test.lock1.unlockWrite(readLock);
            }

            readLock = test.lock2.writeLock();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                test.lock2.unlockWrite(readLock);
            }


        }

    }
}



class RunB implements Runnable{
    DeaiLockTest test;
    public RunB(DeaiLockTest test) {
        this.test = test;
    }

    @Override
    public void run() {
        while (true){
            long readLock = test.lock2.writeLock();
            try {
                System.out.println("B...."+new Date());
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
//                test.lock2.unlockWrite(readLock);
            }

            readLock = test.lock1.writeLock();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                test.lock1.unlockWrite(readLock);
            }


        }

    }
}