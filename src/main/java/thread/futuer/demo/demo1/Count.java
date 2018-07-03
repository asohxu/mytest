package thread.futuer.demo.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author huang_kangjie
 * @create 2018-07-03 14:17
 **/
public class Count {
     public static void main(String[] args) throws InterruptedException {
          long start = System.currentTimeMillis();
          Count count = new Count();
          List<Integer> res = new ArrayList<>();
          res.add(count.random());
          res.add(count.random());
          res.add(count.random());
          res.add(count.random());
          int totle =0;
          for (int i = 0; i < res.size(); i++) {
               totle += res.get(i);
               System.out.println("正在计算：totle = " + totle);
          }
          long end = System.currentTimeMillis();
          System.out.println("运算结束 耗时："+(end-start)+"ms  totle："+totle );
          System.out.println("退出main线程！");
     }

     int random() throws InterruptedException{
          Thread.sleep(1000); //
          return new Random().nextInt(100);
     }

}
