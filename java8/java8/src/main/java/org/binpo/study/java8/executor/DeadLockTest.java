package org.binpo.study.java8.executor;

/**
 * @Author yinhao.zhang
 * @Date 2019/10/31 17:53
 **/
public class DeadLockTest extends Thread{

    private String first;
    private String second;

    private String name;

    public DeadLockTest(String name ,String first,String second){
        this.first = first;
        this.second = second;
        this.name = name;
    }

    @Override
    public void run(){
        synchronized (first){
            System.out.println("threadName:"+name+" first:"+first);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (second){
                System.out.println("threadName:"+name+" second:"+second);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String locka = "locka";
        String lockb = "lockb";
        DeadLockTest thread1 = new DeadLockTest("Thread1", locka, lockb);
        DeadLockTest thread2 = new DeadLockTest("Thread2", lockb, locka);
        thread1.start();
        thread2.start();
        thread2.join();
        thread1.join();

    }

}
