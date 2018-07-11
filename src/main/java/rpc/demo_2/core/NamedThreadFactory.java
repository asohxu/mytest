package rpc.demo_2.core;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *现在再向大家展示一下NettyRPC核心包：newlandframework.netty.rpc.core的关键部分实现代码，首先是业务线程池相关类的实现代码
 * 线程工厂定义实现
 * @author huang_kangjie
 * @create 2018-07-11 10:39
 **/
public class NamedThreadFactory implements ThreadFactory {

     private static final AtomicInteger threadNumber = new AtomicInteger(1);

     private final AtomicInteger mThreadNum = new AtomicInteger(1);

     private final String prefix;

     private final boolean daemoThread;

     private final ThreadGroup threadGroup;

     public NamedThreadFactory() {
          this("rpcserver-threadpool-" + threadNumber.getAndIncrement(), false);
     }

     public NamedThreadFactory(String prefix) {
          this(prefix, false);
     }

     public NamedThreadFactory(String prefix, boolean daemo) {
          this.prefix = prefix + "-thread-";
          daemoThread = daemo;
          SecurityManager s = System.getSecurityManager();
          threadGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
     }

     public Thread newThread(Runnable runnable) {
          String name = prefix + mThreadNum.getAndIncrement();
          Thread ret = new Thread(threadGroup, runnable, name, 0);
          ret.setDaemon(daemoThread);
          return ret;
     }

     public ThreadGroup getThreadGroup() {
          return threadGroup;
     }
}
