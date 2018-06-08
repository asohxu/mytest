package lock_test.lock.my;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @描述：
 *
 * @author 作者 : huang_kangjie
 * @date 创建时间：2018年3月15日
 * @version v1.0.
 * 
 */
public class TaskThread implements Runnable{
	
	private int user ;
	
	public TaskThread(int user){
		this.user = user;
	}
	
	private Lock lock = new ReentrantLock();

	@Override
	public void run() {
		try {
			System.out.println("用户 user = " + user + "开始抢购。。。");
			lock.lock();
			if(contants.num > 0){
				contants.num = contants.num -1;
				System.out.println("用户 user = " + user + "；获得锁。。。成功抢购。");
				contants.successUser.add(user);
			}else{
				System.out.println("用户 user = " + user + "获得锁。。。但是来迟了，商品已经抢完。");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("用户 user = " + user + "失去锁。。。");
		}
	}

}
