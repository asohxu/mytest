package thread.futuer;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author huang_kangjie
 * @create 2018-07-03 14:10
 **/
public class TaskWorker implements Callable<BackInfoBean> {
     private String name;

     public TaskWorker(String name ) {
          this.name = name;
     }

     @Override
     public BackInfoBean call() throws Exception {
          BackInfoBean bean = new BackInfoBean();
          int number = new Random().nextInt(10);
          bean.setCode(number);
          bean.setMsg(name + ": 的 msg number is " + number);
          System.out.println("线程：" + name + " 开始睡眠:"  + (number * 1000) + " ms");
          Thread.sleep(number * 1000);
          System.out.println("线程：" + name + " 被唤醒" );
          return bean;
     }

}
