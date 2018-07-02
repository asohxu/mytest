package localthread;

/**
 * @author huang_kangjie
 * @create 2018-07-02 15:16
 **/
public class StringTask implements Runnable {

     private String name;

     private final ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();

     public StringTask(){}

     public StringTask(String name){
          this.name = name;
     }

     @Override
     public void run() {
          try {
               while (true) {
                    String number;
                    if(null == this.threadLocal.get()){
                         number = "A";
                    } else {
                         number = (String) this.threadLocal.get();
                    }
                    System.out.println("String 线程：【" + name + "】: " + number);
                    number += "-";
                    this.threadLocal.set(number);
                    Thread.sleep(1000);
               }

          } catch (Exception e) {

          }
     }

}
