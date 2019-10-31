package org.binpo.study.java8.lock;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
	
	
	
	public static void printNow(String str ) {
		System.out.println(str + ":"+new Date());
	}
	
	

	int x = 0;
	int y = 0;
	private StampedLock lock = new StampedLock();

	public static void main(String[] args) throws IOException {
		StampedLockTest test = new StampedLockTest();
//		for (int i = 0; i < 50; i++) {
//			new WriteThread(test).start();
//		}
//
//		while (true) {
//			new ReadThread(test).start();
//		}
		
		
		
		new WriteThreadSleepBefore(test).start();
		new ReadThreadSleepBefore(test).start();
		

		System.in.read();
		
		
		
		
		
		
	}

	public void printValue() {
		long readLock = lock.readLock();
		try {
			System.out.println("x:" + x + "   y:" + y);
		} finally {
			lock.unlockRead(readLock);
		}
	}

	public void distanceprintValue() {
		long readLock = lock.tryOptimisticRead();

		//如果在以上代码执行过程中发现
		if (!lock.validate(readLock)) {
			readLock = lock.readLock();
			try {
				System.out.println("in lock x:" + x + "   y:" + y);
			} finally {
				lock.unlockRead(readLock);
			}
		} else {
			System.out.println("un lock x:" + x + "   y:" + y);
		}
	}

	/**
	 * tryOptimisticRead 乐观锁 在执行tryOptimisticRead 后到 lock.validate(readLock);
     * 如果 有其他线程获取过锁：
     *  那么lock.validate(readLock) 返回 false
     * 如果 其他线程未获去过锁
     *  那么 lock.validate(readLock) 返回 true ，可以对贡献数据进行操作
	 * @throws InterruptedException
	 */
	public void printValueSleepBefore() throws InterruptedException {
		long readLock = lock.tryOptimisticRead();
		StampedLockTest.printNow("printValueSleepBefore:readLock:"+readLock);
		
		StampedLockTest.printNow("printValueSleepBefore:befare");
		Thread.sleep(6000);
		StampedLockTest.printNow("printValueSleepBefore:after");

		//如果在以上代码执行过程中发现
		boolean validate = lock.validate(readLock);
		System.out.println("validate :" + validate);
		if (!validate) {
			readLock = lock.readLock();
			StampedLockTest.printNow("悲观读锁获取锁的时间");
			try {
				System.out.println("in lock x:" + x + "   y:" + y);
			} finally {
				lock.unlockRead(readLock);
			}
		} else {
			System.out.println("un lock x:" + x + "   y:" + y);
		}
		
		Thread.sleep(9000);
		validate = lock.validate(readLock);
		System.out.println("validate sleep 9s after :" + validate);
	}
	

	public void add(int c, int d) {
		long writeLock = lock.writeLock();
		try {
			x += c;
			y += d;
		} finally {
			lock.unlockWrite(writeLock);
		}
	}

	public void addsleep10(int c, int d) throws InterruptedException {
		long writeLock = lock.writeLock();
		try {
			StampedLockTest.printNow("addsleep10:befare");
//			Thread.sleep(3000);
			StampedLockTest.printNow("addsleep10:after");
			x += c;
			y += d;
		} finally {
			lock.unlockWrite(writeLock);
		}
	}
}

class ReadThread extends Thread {
	StampedLockTest test = null;

	public ReadThread(StampedLockTest test) {
		this.test = test;
	}

	@Override
	public void run() {
//		test.printValue();
		test.distanceprintValue();
	}
}

class WriteThread extends Thread {
	StampedLockTest test = null;

	public WriteThread(StampedLockTest test) {
		this.test = test;
	}

	@Override
	public void run() {
		try {
			test.addsleep10(1, 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class WriteThreadSleepBefore extends Thread {
	StampedLockTest test = null;

	public WriteThreadSleepBefore(StampedLockTest test) {
		this.test = test;
	}

	@Override
	public void run() {
		try {
			StampedLockTest.printNow("WriteThreadSleepBefore:before");
			Thread.sleep(5000);
			StampedLockTest.printNow("WriteThreadSleepBefore:after");
			test.addsleep10(1, 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ReadThreadSleepBefore extends Thread {
	StampedLockTest test = null;

	public ReadThreadSleepBefore(StampedLockTest test) {
		this.test = test;
	}

	@Override
	public void run() {
		try {
			test.printValueSleepBefore();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}



