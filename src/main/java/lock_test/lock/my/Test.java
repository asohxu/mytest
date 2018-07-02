package lock_test.lock.my;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
		
		List<TaskThread> cacheList = new ArrayList<>(150);
		
		//构建线程池
		ExecutorService executor = Executors.newCachedThreadPool();
		//总用户100人
		int user = 100;
		for (int i = 0; i < user; i++) {
			cacheList.add(new TaskThread(i));
		}
		System.out.println("所有用户准备完毕");
		try {
			System.out.println("抢购倒计时    3");
			Thread.sleep(1000);
			System.out.println("抢购倒计时    2");
			Thread.sleep(1000);
			System.out.println("抢购倒计时    1");
			Thread.sleep(1000);
			for (TaskThread task : cacheList) {
				executor.submit(task);
			}
			
			executor.shutdown();
			System.out.println("====================");
			for (Integer u : contants.successUser) {
				System.out.println(u);
			}
		} catch (Exception e) {
		}
		
	}
}
