package test.thread.callable_test;
/**
 * @描述：
 *
 * @author 作者 : huang_kangjie
 * @date 创建时间：2018年3月15日
 * @version v1.0.
 * 
 */
import com.sun.org.apache.xalan.internal.utils.FeatureManager;

import java.util.concurrent.*;

/**
 * Created by qiu.li on 2015/9/21.
 */
public class TestCallable implements Callable<Boolean> {

    int i;

    public static void main(String[] args) {

        ExecutorService runnableService = Executors.newFixedThreadPool(3);

        Future<Boolean> r1 =  runnableService.submit(new TestCallable(1));
        Future<Boolean> r2 =  runnableService.submit(new TestCallable(2));
        Future<Boolean> r3 =  runnableService.submit(new TestCallable(3));
        try {
            boolean b2 = r2.get(); //r2先跑
            boolean b3 = r3.get(); //r3先跑
            System.out.println(b2);
            System.out.println(b3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        r1.cancel(true);//r1是死循环，现在退出
        runnableService.shutdownNow();
    }

    public TestCallable(int i){
        this.i = i;
    }

    @Override
    public Boolean call() {
        try {
        	
        	System.out.println("执行的 i = " + i);
            switch (i){
                case 1:
                    while(true) {
                        System.out.println(Thread.currentThread().getName() + ";i:" + this.i); //第一个线程
                        Thread.sleep(200);
                    }
                default:
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + ";其他线程 i:" + this.i); //其他线程
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
