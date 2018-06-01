package test.thread.waitNotify;

import java.util.HashMap;
import java.util.Map;

/**
 * 类说明
 *
 * @author 作者 : huang_kangjie
 * @version 创建时间：2017年3月2日
 * 
 */
public class MyTest {

	public static Map<String, Object> objectMap = new HashMap<String, Object>();
	
	
	public MyTest(){
		
	}
	
	public static void main(String[] args) {
		MyTest test = new MyTest();
		test.startThreadA();
		test.startThreadB();
		test.startThreadC();
		
		try {
			Thread.sleep(3 * 1000);
			Object objectA = objectMap.get("A");
			synchronized (objectA) {
				objectA.notifyAll();
				System.out.println("唤醒的代码");
			}
			Thread.sleep(1 * 1000);
			Object objectB = objectMap.get("B");
			synchronized (objectB) {
				objectB.notifyAll();
			}
			Thread.sleep(1 * 1000);
			Object objectC = objectMap.get("C");
			synchronized (objectC) {
				objectC.notifyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
     * 启动线程A
     */
    public void startThreadA() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
            	Object object = new Object();
            	objectMap.put("A", object);
            	
                synchronized (object) {
                	try {
                		System.out.println("线程 A 等待...");
						object.wait();
						System.out.println("线程 A 唤醒，并结束...");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
                
                System.out.println("A唤醒后的代码");
            }
        });
        t.start();
    }
    
    /**
     * 启动线程B
     */
    public void startThreadB() {
    	Thread t = new Thread(new Runnable() {
    		@Override
    		public void run() {
    			Object object = new Object();
    			objectMap.put("B", object);
    			
    			synchronized (object) {
    				try {
    					System.out.println("线程 B 等待...");
    					object.wait();
    					System.out.println("线程 B 唤醒，并结束...");
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
    			}
    		}
    	});
    	t.start();
    }
    
    /**
     * 启动线程C
     */
    public void startThreadC() {
    	Thread t = new Thread(new Runnable() {
    		@Override
    		public void run() {
    			Object object = new Object();
    			objectMap.put("C", object);
    			
    			synchronized (object) {
    				try {
    					System.out.println("线程 C 等待...");
    					object.wait();
    					System.out.println("线程 C 唤醒，并结束...");
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
    			}
    		}
    	});
    	t.start();
    }
	
}
