package thread.synchronized1;

import java.util.Random;

/**
 * 取钱者
 *
 * @author 作者 : huang_kangjie
 * @version 创建时间：2017年3月2日
 * 
 */
public class ClientGetMoney implements Runnable{

	private String name;
	private ThreadLocal<Integer> local = new ThreadLocal<>();
	public ClientGetMoney(String name) {
		this.name = name;
		this.local.set(0);
	}
	

	@Override
	public void run() {
		
		while (true) {
			//System.out.println(name + " : 当前用户["+ClientGetMoney.class+ "]取钱【之前】 : "+MainTest.bank.getMoney());
			int number = 0;
			synchronized(MainTest.bank){
				Integer money = MainTest.bank.getMoney();
				number = new Random().nextInt(10);
				if(money < 10 ){
					//System.out.println(name + " : 当前用户["+ClientGetMoney.class+ "],账户余额不足，没法取钱");
				}else{
					money = MainTest.bank.getMoney() - number;
					MainTest.bank.setMoney(money);
					if(null == local.get()){
						local.set(0);
					}
					this.local.set(local.get() + number);
				}
				if(name.equals("[取钱1]")){
					System.out.println(name + " ：当前取钱" + number + " ; 累计取钱" + this.local.get() + "总金额：" + money);
				}

			}
			try {
				Thread.sleep(1000 * (number));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("取钱 A : 当前用户["+ClientGetMoney.class+ "]取钱【之后】 : "+MainTest.bank.getMoney());

			try {
				Thread.sleep(3 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
