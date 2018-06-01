package test.thread.synchronized1;
/**
 * 取钱者
 *
 * @author 作者 : huang_kangjie
 * @version 创建时间：2017年3月2日
 * 
 */
public class ClientGetMoney implements Runnable{
	
	public Integer getCount = 0;
	@Override
	public void run() {
		
		while (true) {
			System.out.println("取钱 A : 当前用户["+ClientGetMoney.class+ "]取钱【之前】 : "+MainTest.bank.getMoney());
			synchronized(MainTest.bank){
				Integer money = MainTest.bank.getMoney();
				if(money < 10 ){
					System.out.println("取钱 A : 当前用户["+ClientGetMoney.class+ "],账户余额不足，没法取钱");
				}else{
					money = MainTest.bank.getMoney() - 10;
					MainTest.bank.setMoney(money);
					getCount += 10;
				}
			}
//			System.out.println("取钱 A : 当前用户["+ClientGetMoney.class+ "]取钱【之后】 : "+MainTest.bank.getMoney());
			System.out.println("取钱A ：累计取钱" + getCount);
//			try {
//				Thread.sleep(5 * 1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		
	}

}
