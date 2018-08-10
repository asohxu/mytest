package thread.threadpool.demo_2;

import java.util.concurrent.RejectedExecutionException;

/**
 * @author huang_kangjie
 * @create 2018-08-06 11:39
 **/
public class Test {

     public static void main(String[] args) {

          //启动监听类
          new Thread(new ThreadState()).start();

          for (int i = 0; i < 50 ; i++)  {
               ThreadTask task = new ThreadTask(i + "");
               try {
                    ThreadPool.POOL.execute(task);
               } catch (RejectedExecutionException e) {
                    System.err.println("线程名："+ i +"; 线程池已满。。。");
               }


          }

          ThreadPool.POOL.shutdown();

     }
}
