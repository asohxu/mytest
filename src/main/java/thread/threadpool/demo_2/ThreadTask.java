package thread.threadpool.demo_2;

import java.util.Random;

/**
 * @author huang_kangjie
 * @create 2018-08-06 11:13
 **/
public class ThreadTask implements Runnable{

     /**
      * 线程名称
      */
     private String name;

     public ThreadTask(String name) {
          this.name = name;
     }

     @Override
     public void run() {
          System.out.println("当前线程名称：" + name);
          try {
               int sleep = new Random().nextInt(5);

               Thread.sleep(sleep * 1000);
               System.out.println("当前线程 [" + name + "] 执行完毕");
          } catch (Exception e) {

          }
     }
}
