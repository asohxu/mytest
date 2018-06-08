package thread.threadpool.main;

import test.thread.threadpool.pool.MyThreadPool;
import thread.threadpool.pool.MyTheadListener;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * 初始化线程池
 * @author Mr-H
 *
 */
public class TreadMain {

	public static void main(String[] args) {
		ThreadPoolExecutor pool = MyThreadPool.getPoolInstance();
		Thread listener = new MyTheadListener();
		listener.start();//启动监听
		//pool.shutdown();
	}
}
