package test.thread.synchronized1;
/**
 * 构建模拟两个人去银行存取钱
 * 一个区存钱
 * 一个取钱
 *
 * @author 作者 : huang_kangjie
 * @version 创建时间：2017年3月2日
 * 
 */
public class MainTest {
	
	public static Bank bank = new Bank();

	/**
	 * A：每秒往银行中存5块
	 * B：每秒在银行取钱10块，当前不足的时候取不到，但是接着有向银行取钱
	 * 
	 * 要求：
	 * 1、每次存钱和取钱【之前】都要查询一次银行总账。
	 * 2、每次存钱和取钱【之后】都要查询一次银行总账。
	 */
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 10024; i++) {
			Thread s1 = new Thread(new ClientSaveMoney());
			s1.start();
		}
		
		Thread s1 = new Thread(new ClientSaveMoney());
		Thread s2 = new Thread(new ClientSaveMoney());
		Thread s3 = new Thread(new ClientSaveMoney());
		Thread s4 = new Thread(new ClientSaveMoney());
		Thread s5 = new Thread(new ClientSaveMoney());
		
		Thread t1 = new Thread(new ClientGetMoney());
//		Thread t2 = new Thread(new ClientGetMoney());
//		Thread t3 = new Thread(new ClientGetMoney());
//		Thread t4 = new Thread(new ClientGetMoney());
//		Thread t5 = new Thread(new ClientGetMoney());


//		s1.start();
//		s2.start();
//		s3.start();
//		s4.start();
//		s5.start();
//		try {
//			Thread.sleep(10*1000);
//			t1.start();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
//		t2.start();
//		t3.start();
//		t4.start();
//		t5.start();
		
		
	}
}
