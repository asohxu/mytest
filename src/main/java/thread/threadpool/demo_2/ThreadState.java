package thread.threadpool.demo_2;

/**
 * @author huang_kangjie
 * @create 2018-08-06 11:17
 **/
public class ThreadState implements  Runnable{

     @Override
     public void run() {

          try {

               System.out.println("线程监听类启动！！！");

               while (true) {
                    System.out.println("线程池pool的状态：" +
                            "activeCount = " + ThreadPool.POOL.getActiveCount() + ";" +                  //激活的线程数， 此参数 <= 小于等于 最大线程数，  有锁
                            "queueSize = " + ThreadPool.POOL.getQueue().size() + ";" +                   //线程池缓存队列大小
                            "completedTaskCount = " + ThreadPool.POOL.getCompletedTaskCount() +";" +     //已完成任务总数， 此参数 <= 小于等于 任务总数，  有锁
                            "taskCount = " + ThreadPool.POOL.getTaskCount() + ";" +                      //任务总数，                                 有锁
                            "corePoolSize = " + ThreadPool.POOL.getCorePoolSize() + ";" +                //核心线程数
                            "largestPoolSize = " + ThreadPool.POOL.getLargestPoolSize()+ ";" +           //线程池支持的最大线程数                        有锁
                            "maximumPoolSize = " + ThreadPool.POOL.getMaximumPoolSize() + ";" +          //线程池支持的最大线程数
                            "poolSize = " + ThreadPool.POOL.getPoolSize() + ";"                          //线程池工作线程数， 此参数与activeCount保持一致， 有锁
                    );


                    Thread.sleep(1000);
               }

          } catch (Exception e) {
               e.printStackTrace();
          }

     }
}
