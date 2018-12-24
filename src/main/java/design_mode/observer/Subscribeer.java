package design_mode.observer;

import java.util.Random;

/**
 * 订阅者：
 * 模拟服务器，
 * 服务器在启动的时候注册到注册中心，掉线后，主动通知注册中心
 * @author huang_kangjie
 * @date 2018-12-24 15:58
 * @since 1.0.3
 **/
public class Subscribeer implements Runnable{

     private String ip;

     public Subscribeer(){}

     public Subscribeer(String ip) {
          this.ip = ip;
     }

     /**
      * 模拟服务器启动
      */
     public void bootstrap() {
          System.out.println("服务器：ip = " + this.ip + "已启动" );
          Publisher.register(this.ip);
     }

     /**
      * 模拟服务器运行
      */
     @Override
     public void run() {
          try {
               System.out.println("服务器：ip = " + this.ip + "已启动" );
               Publisher.register(this.ip);
               while (true) {
                    int num = new Random().nextInt(10);
                    if(num == 5) {
                         System.out.println("服务器：ip = " + ip + " 掉线.....");
                         Publisher.shutdown(this.ip);
                         break;
                    } else {
                         //System.out.println("服务器 ip = " + ip + " ,生成随机数 num = " + num);
                    }
                    Thread.sleep(500);
               }
          } catch (Exception e) {
               e.printStackTrace();
          }
     }




}
