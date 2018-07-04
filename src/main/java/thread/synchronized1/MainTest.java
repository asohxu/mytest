package thread.synchronized1;
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
	
	public static BankBean bank = new BankBean();

	/**
	 * A：每秒往银行中存5块
	 * B：每秒在银行取钱10块，当前不足的时候取不到，但是接着有向银行取钱
	 * 
	 * 要求：
	 * 1、每次存钱和取钱【之前】都要查询一次银行总账。
	 * 2、每次存钱和取钱【之后】都要查询一次银行总账。
	 */
	
	public static void main(String[] args) {
		
		//for (int i = 0; i < 10024; i++) {
		//	Thread s1 = new Thread(new ClientSaveMoney());
		//	s1.start();
		//}
		
		Thread s1 = new Thread(new ClientSaveMoney("[存钱A]"));
		Thread s2 = new Thread(new ClientSaveMoney("[存钱B]"));
		Thread s3 = new Thread(new ClientSaveMoney("[存钱C]"));
		Thread s4 = new Thread(new ClientSaveMoney("[存钱D]"));
		Thread s5 = new Thread(new ClientSaveMoney("[存钱E]"));
		
		Thread t1 = new Thread(new ClientGetMoney("[取钱1]"));
		Thread t2 = new Thread(new ClientGetMoney("[取钱2]"));
		Thread t3 = new Thread(new ClientGetMoney("[取钱3]"));
		Thread t4 = new Thread(new ClientGetMoney("[取钱4]"));
		Thread t5 = new Thread(new ClientGetMoney("[取钱5]"));
		Thread t6 = new Thread(new ClientGetMoney("[取钱6]"));
		Thread t7 = new Thread(new ClientGetMoney("[取钱7]"));
		Thread t8 = new Thread(new ClientGetMoney("[取钱8]"));
		Thread t9 = new Thread(new ClientGetMoney("[取钱9]"));
		Thread t10 = new Thread(new ClientGetMoney("[取钱10]"));


		s1.start();
		s2.start();
		s3.start();
		s4.start();
		s5.start();
		try {
			for (int i = 10; i > 0; i--) {
				System.out.println(i + "秒后开始取钱...");
				Thread.sleep(1000);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();

		
	}
}
