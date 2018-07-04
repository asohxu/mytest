package thread.threadpool.demo_1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 默认实现线程的5中实现方式
 * @author huang_kangjie
 * @create 2018-07-04 10:54
 **/
public class ThreadPoolDemo1 {

     //固定边长的线程池
     private static final ExecutorService executer1 = Executors.newFixedThreadPool(10);
     //可缓存的线程池
     private static final ExecutorService executer2 = Executors.newCachedThreadPool();
     //单利的线程池
     private static final ExecutorService executer3 = Executors.newSingleThreadExecutor();
     //保持核心线程的线程池
     private static final ExecutorService executer4 = Executors.newScheduledThreadPool(10);

     /** 初始化缓存线程池 */
     public static ThreadPoolExecutor CACHE_POOL = new ThreadPoolExecutor(
             10,              //核心线程数
             65535,       //最大线程数
             0,              //存活时间
             TimeUnit.SECONDS,            //时间单位
             new LinkedBlockingQueue<Runnable>(100000),     //任务可缓存的队列
             new ThreadPoolExecutor.DiscardPolicy()                //当线程池满了以后的策略

             /**
              *
              * 线程池满了以后的策略：
              *
              * AbortPolicy()            当线程池中的数量等于最大线程数时、直接抛出抛出java.util.concurrent.RejectedExecutionException异常
              * CallerRunsPolicy()       当线程池中的数量等于最大线程数时、重试执行当前的任务，交由调用者线程来执行任务
              * DiscardOldestPolicy()    当线程池中的数量等于最大线程数时、抛弃线程池中最后一个要执行的任务，并执行新传入的任务
              * DiscardPolicy()          当线程池中的数量等于最大线程数时，不做任何动作
              *
              */
     );


     public static void main(String[] args) {

     }


}
