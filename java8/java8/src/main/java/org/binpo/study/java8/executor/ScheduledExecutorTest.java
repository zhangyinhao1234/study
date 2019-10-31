package org.binpo.study.java8.executor;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.*;

public class ScheduledExecutorTest {

    /**
     * 添加的任务是有序的，并且只有一个线程处于活动中
     */
	final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();




    public static void main(String[] args) throws InterruptedException, ExecutionException {
                ScheduledExecutorTest scheduledExecutorTest = new ScheduledExecutorTest();
        scheduledExecutorTest.service.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				try {
					scheduledExecutorTest.printTimeSleep1();
				} catch (Exception e){
				    e.printStackTrace();
                }
			}
			//延迟 500 毫秒 每隔1000 毫秒执行
		}, 500, 1000, TimeUnit.MILLISECONDS);

		scheduledExecutorTest.service.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					scheduledExecutorTest.printTimeSleep10();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 500, 1000, TimeUnit.MILLISECONDS);

		scheduledExecutorTest.service.schedule(new Runnable() {
            @Override
		    public void run() {
                System.out.println("一次性的任务。。。。。。。。。。");
            }
        },500,TimeUnit.MILLISECONDS);


        ScheduledFuture<Integer> schedule = scheduledExecutorTest.service.schedule(new Callable<Integer>() {
            @Override
            public Integer call() throws InterruptedException {
                int i = new Random().nextInt(10);
                Thread.sleep(5000);
                return i;
            }
        }, 0, TimeUnit.MILLISECONDS);

        System.out.println(schedule.get());

    }
	
	
	
	
	private void printTimeSleep10() throws InterruptedException {
		printTime("thread1");
		if(true){
		    throw  new RuntimeException();
        }
	}
	
	private void printTimeSleep1() throws InterruptedException {
		Thread.sleep(3000);
		printTime("thread2");
	}
	
	private void printTime(String threadName) {
		System.out.println("threadName:"+threadName+"  time:"+new Date());
	}

}
