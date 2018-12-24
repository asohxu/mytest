package design_mode.observer;

/**
 * @author huang_kangjie
 * @date 2018-12-24 16:03
 * @since 1.0.3
 **/
public class Test {

     public static void main(String[] args) {
          for(int i = 1; i <= 15; i++) {
               Subscribeer sb = new Subscribeer("127.0.0." + i);
               Thread t = new Thread(sb);
               t.start();
          }

          try {
               //模拟，服务器等待启动
               Thread.sleep(5000);
          } catch (Exception e) {
               e.printStackTrace();
          }

          long st = System.currentTimeMillis();
          while (true) {
               if(Publisher.isLive()) {
                    System.out.println("所有的服务器已宕机！！！！");
                    break;
               }
               try {
                    Thread.sleep(1000);
               } catch (Exception e) {
                    e.printStackTrace();
               }
          }
          long et = System.currentTimeMillis();
          System.out.println("服务器运行时间：time = " + (et - st) / 1000);

     }

}
