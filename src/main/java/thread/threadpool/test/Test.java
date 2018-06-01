package test.thread.threadpool.test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import test.thread.threadpool.HttpKeyConstant;

/**
 * 类说明
 *
 * @author 作者 : huang_kangjie
 * @version 创建时间：2017年3月7日
 * 
 */
public class Test {
	
	public static ThreadPoolExecutor PRODUCERPOOL = new ThreadPoolExecutor(5,
           5, 0, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(3),
            new ThreadPoolExecutor.DiscardOldestPolicy());
	

	public static void main(String[] args) {
		
		
		
		for (int i = 0; i < 100; i++) {
			
			PRODUCERPOOL.execute(new ThreadTask(i+""));
			System.out.println("正在运行的线程" + PRODUCERPOOL.getActiveCount());
		}
		
		new Thread(new ThreadTask( "0>>>")).start();
		
		new Thread(new taskOne()).start();
		new Thread(new taskTwo()).start();
		
		startOne();
		startTwo();
		
		PRODUCERPOOL.shutdown();
		System.out.println("正在运行的线程" + PRODUCERPOOL.getActiveCount());
	}
	
	public static void startOne(){
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("start one");
			}
		});
		t.start();
	}
	public static void startTwo(){
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("start two");
			}
		});
		t.start();
	}
}

class taskOne implements Runnable{

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("task one");
		
	}
	
}

class taskTwo implements Runnable{
	
	@Override
	public void run() {
		System.out.println("task two");
		
	}
	
}
