package test.thread.threadpool.main;

import java.util.concurrent.ThreadPoolExecutor;

import test.thread.threadpool.pool.MyTheadListener;
import test.thread.threadpool.pool.MyThreadPool;


/**
 * 初始化线程池
 * @author Mr-H
 *
 */
public class TreadMain {

	public static void main(String[] args) {
		ThreadPoolExecutor pool = MyThreadPool.getPoolInstance();
		Thread listener = new  MyTheadListener();
		listener.start();//启动监听
		//pool.shutdown();
	}
}
