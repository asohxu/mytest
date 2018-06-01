package test.thread.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @描述：
 *
 * @author 作者 : huang_kangjie
 * @date 创建时间：2017年7月26日
 * @version v1.0.
 * 
 */
public class CountDountLathTest {
	
	public static void main(String[] args) {
		doTest();
	}

	public static void doTest(){
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);//创建一个主线程等待所有的子线程结束，唤醒后面的程序
        final CountDownLatch cdAnswer = new CountDownLatch(60);
        
        for (int i = 0; i < 60; i++) {
        	ServerTast tast = new ServerTast(60);
        	service.execute(tast);
		}
        
        try {
        	cdOrder.countDown();
			cdAnswer.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        service.shutdown();
	}
}

class ServerTast implements Runnable{
	//核心线程数
	int threadSize = 0;
	
	public ServerTast(int size){
		this.threadSize = size;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " >>>>>>>>>>>>>> 开始执行任务");
		try {
//			Thread.sleep(5000);
		} catch (Exception e) {
		}
		System.out.println(Thread.currentThread().getName() + " >>>>>>>>>>>>>> 结束执行任务");
	}
	
}
