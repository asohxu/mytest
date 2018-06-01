package test.thread.waitNotify;
/**
 * 类说明
 *
 * @author 作者 : huang_kangjie
 * @version 创建时间：2017年3月2日
 * 
 */
public class ThreadFactory {

    // 线程A是否处于等待状态的标志
    private boolean isThreadAWaiting;
    // 线程B是否处于等待状态的标志
    private boolean isThreadBWaiting;
    // 线程C是否处于等待状态的标志
    private boolean isThreadCWaiting;

    public ThreadFactory() {
        isThreadAWaiting = true;
        isThreadBWaiting = true;
        isThreadCWaiting = true;
    }

    /**
     * 对象锁
     */
    private final Object object = new Object();
    
    
    public static void main(String[] args) {
        ThreadFactory factory = new ThreadFactory();
        factory.startThreadA();
        factory.startThreadB();
        factory.startThreadC();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        factory.startWakenThread();
    }

    /**
     * 该线程作为一个唤醒线程
     */
    public void startWakenThread() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("唤醒线程开始执行...");
                    // 首先释放线程A
                    quitThreadA();
                }
            }
        });
        t.start();
    }

    /**
     * 启动线程A
     */
    public void startThreadA() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("线程A开始等待...");
                    try {
                        for (; ; ) {
                            if (!isThreadAWaiting) break;
                            object.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程A结束...");
                    // 线程A结束后，暂停2秒释放线程B
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    quitThreadB();
                }
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
                synchronized (object) {
                    System.out.println("线程B开始等待...");
                    try {
                        for (; ; ) {
                            if (!isThreadBWaiting) break;
                            object.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程B结束...");
                    // 线程B结束后，暂停2秒释放线程C
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    quitThreadC();
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
                synchronized (object) {
                    System.out.println("线程C开始等待...");
                    try {
                        for (; ; ) {
                            if (!isThreadCWaiting) break;
                            object.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程C结束...");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("所有线程执行完毕！");
                }
            }
        });
        t.start();
    }

    /**
     * 线程A退出等待
     */
    private void quitThreadA() {
        isThreadAWaiting = false;
        object.notify();
    }
    
    /**
     * 线程B退出等待
     */
    private void quitThreadB() {
        isThreadBWaiting = false;
        object.notify();
    }
    
    /**
     * 线程C退出等待
     */
    private void quitThreadC() {
        isThreadCWaiting = false;
        object.notify();
    }
}
