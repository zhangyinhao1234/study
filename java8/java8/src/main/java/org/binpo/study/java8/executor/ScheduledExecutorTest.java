package org.binpo.study.java8.executor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {
	final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	
	public static void main(String[] args) throws InterruptedException {
		ScheduledExecutorTest scheduledExecutorTest = new ScheduledExecutorTest();

		
		
		scheduledExecutorTest.service.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				try {
					scheduledExecutorTest.printTimeSleep1();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 500, 1000, TimeUnit.MILLISECONDS);
		
		
		scheduledExecutorTest.service.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				try {
					scheduledExecutorTest.printTimeSleep10();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 500, 1000, TimeUnit.MILLISECONDS);
		
		
//		Thread.sleep(6000);
//		scheduledExecutorTest.service.shutdown();
		
	}
	
	
	
	
	private void printTimeSleep10() throws InterruptedException {
		printTime("thread1");
	}
	
	private void printTimeSleep1() throws InterruptedException {
		Thread.sleep(3000);
		printTime("thread2");
	}
	
	private void printTime(String threadName) {
		System.out.println("threadName:"+threadName+"  time:"+new Date());
	}

}
