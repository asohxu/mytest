package thread.threadpool.demo_2;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author huang_kangjie
 * @create 2018-08-06 11:10
 **/
public class ThreadPool {

     /** 初始化缓存线程池 */
     public static final  ThreadPoolExecutor POOL = new ThreadPoolExecutor(
             2,              //核心线程数
             5,          //最大线程数
             0,             //存活时间
             TimeUnit.SECONDS,          //时间单位
             new LinkedBlockingQueue<Runnable>(10),     //任务可缓存的队列
             new ThreadPoolExecutor.AbortPolicy()              //当线程池满了以后的策略

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
}
