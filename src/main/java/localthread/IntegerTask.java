package localthread;

/**
 * @author huang_kangjie
 * @create 2018-07-02 15:16
 **/
public class IntegerTask implements Runnable {

     private String name;

     private final ThreadLocal<Object> threadLocal = new ThreadLocal<Object>(){
          /**
           * ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值
           */
          @Override
          protected Object initialValue()
          {
               System.out.println("调用get方法时，当前线程共享变量没有设置，调用initialValue获取默认值！");
               return null;
          }
     };

     public IntegerTask(){}

     public IntegerTask(String name){
          this.name = name;
     }

     @Override
     public void run() {
          try {
               while (true) {
                    int number;
                    if(null == this.threadLocal.get()){
                         number = 0;
                    } else {
                         number = (int) this.threadLocal.get();
                    }
                    System.out.println("integer 线程：【" + name + "】: " + number);
                    number++;
                    this.threadLocal.set(number);
                    Thread.sleep(1000);
               }

          } catch (Exception e) {

          }
     }
}
