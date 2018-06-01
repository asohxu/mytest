package test.thread.threadpool.test;
/**
 * 类说明
 *
 * @author 作者 : huang_kangjie
 * @version 创建时间：2017年3月7日
 * 
 */
public class ThreadTask implements Runnable {
	
	private String name;
	
	public ThreadTask(){
		
	}
	
	public ThreadTask(String name){
		this.name = name;
	}

	@Override
	public void run() {
		System.err.println(name + " 正在执行任务");
	}
	
}
