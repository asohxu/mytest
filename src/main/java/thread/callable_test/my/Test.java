package test.thread.callable_test.my;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import test.thread.callable_test.my.bean.ResultBean;
import test.thread.callable_test.my.callable.CallableTaskThread;

/**
 * @描述：
 *
 * @author 作者 : huang_kangjie
 * @date 创建时间：2018年3月15日
 * @version v1.0.
 * 
 */
public class Test {

	public static void main(String[] args) {
		//初始化一个线程池，然后创建多个call线程，分别执行不同的方法
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		CallableTaskThread call1 = new CallableTaskThread(1);
		CallableTaskThread call2 = new CallableTaskThread(2);
		CallableTaskThread call3 = new CallableTaskThread(3);
		
		Future<ResultBean> rs1 = executor.submit(call1);
		Future<ResultBean> rs2 = executor.submit(call2);
		Future<ResultBean> rs3 = executor.submit(call3);
		
		executor.shutdown();
		
		try {
			ResultBean rsb2 = rs2.get();
			ResultBean rsb1 = rs1.get();
			ResultBean rsb3 = rs3.get();
			
			System.out.println("线程1 返回code = " + rsb1.getCode() + " ;msg = " + rsb1.getMsg());
			System.out.println("线程2 返回code = " + rsb2.getCode() + " ;msg = " + rsb2.getMsg());
			System.out.println("线程3 返回code = " + rsb3.getCode() + " ;msg = " + rsb3.getMsg());
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}
}
