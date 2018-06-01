package test.thread;
/**
 * 主线程 非主线程
 * 
 * 
 * 1，当你运行这个类的时候，首先是java虚拟机自动创建一个线程,假设为T1,
   	这个线程会调用main方法，main方法是java虚拟机自动调用的，调用的这个线程，
   	即通常我们    说的主线程
   2，ThreadDemo 是你自定义的一个线程，
            当T1线程调用t.start();的时候，此时就启动了线程ThreadDemo，T1线程只是告诉线程ThreadDemo，啊，我通知一下，你可以运行了。
            我还有工作没做完，我先走了，ThreadDemo这个线程运行后， 会自动调用run方法，所以run方法是在threadDemo线程中运行的，  
              System.out.println(currentThread().getName() + "---" + i);打印的是ThreadDemo 线程
	3，T1线程通知threadDemo可以运行以后，他就不管ThreadDemo了，调用t.start()后，
   	 会立即执行下面的代码t.run，因为t.run的调用与普通方法没有任何区别，
     t.run与t.start不一  样，因为java规范规定start启动一个线程，但没规定run方法启动一个线程
     	那么既然run方法没有启动新的线程，那么t.run();方法还是在T1线程中运行的
    	所以t.run();是打印的是main线程
 * 
 * @author huangkangjie
 *
 */
public class MainThread extends Thread {
	public void run() {
		for (int i = 0; i <= 5; i++) {
			try {
				System.out.println(currentThread().getName() + "---" + i);
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		MainThread t = new MainThread();
		t.start();
		t.run();
	}
}
