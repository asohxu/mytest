package thread.synchronized1;
/**
 * 存钱者
 *
 * @author 作者 : huang_kangjie
 * @version 创建时间：2017年3月2日
 * 
 */
public class ClientSaveMoney implements Runnable{
	public Integer saveCount = 0;
	public boolean flag = true;

	private String name;

	public ClientSaveMoney(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		while (flag) {
			
			synchronized(MainTest.bank){
				//System.out.println(name + " : 当前用户["+ClientSaveMoney.class.getName()+ "]存钱【之前】 : "+MainTest.bank.getMoney());
				Integer money = MainTest.bank.getMoney() + 1;
				MainTest.bank.setMoney(money);
				saveCount += 1;				   
				if(MainTest.bank.getMoney() >= 1000000000){
					flag = false;
				}

				//System.out.println("当前用户总账：" + MainTest.bank.getMoney());
			}
			
//			System.out.println("存钱 B : 当前用户["+ClientSaveMoney.class.getName()+ "]存钱【之后】 : "+MainTest.bank.getMoney());
//			System.out.println("存钱 B ：累计存钱" + saveCount);
			try {
				Thread.sleep(10 * 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
