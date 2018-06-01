package test.thread;
/**
 * 交替打印数字
 *
 * @author 作者 : huang_kangjie
 * @version 创建时间：2017年3月3日
 * 
 */
public class PrintNum {
	
	
	public boolean oneFlag = true;
	public boolean twoFlag = false;
	
	public static void main(String[] args) {
		PrintNum print = new PrintNum();
		print.printOne();
		print.printTwo();
	}

	private void printOne(){
		Thread t = new Thread( new Runnable() {
			
			@Override
			public void run() {
				while(true){
					if(oneFlag){
						System.out.println("1");
						oneFlag = false;
						twoFlag = true;
					}
					try {
						Thread.sleep(1000);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		t.start();
	}
	
	private void printTwo(){
		Thread t = new Thread( new Runnable() {
			
			@Override
			public void run() {
				while(true){
					if(twoFlag){
						System.out.println("2");
						twoFlag = false;
						oneFlag = true;
					}
					try {
						Thread.sleep(1000);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		t.start();
	}
}


