package rpc.demo_2.test;

import org.apache.commons.lang.time.StopWatch;
import rpc.demo_2.core.MessageSendExecutor;

import java.util.concurrent.CountDownLatch;

/**
 * rpc并发测试代码
 * @author huang_kangjie
 * @create 2018-07-11 10:57
 **/
public class RpcParallelTest {
     public static void main(String[] args) throws Exception {
          //启动默认的客户端，并绑定好服务器的地址，这个地方可以优化成动态从zookeper获取更好
          final MessageSendExecutor executor = new MessageSendExecutor("127.0.0.1:18888");
          //并行度10000
          int parallel = 10000;

          //开始计时
          StopWatch sw = new StopWatch();
          sw.start();

          CountDownLatch signal = new CountDownLatch(1);
          CountDownLatch finish = new CountDownLatch(parallel);

          for (int index = 0; index < parallel; index++) {
               //申明单个请求
               CalcParallelRequestThread client = new CalcParallelRequestThread(executor, signal, finish, index);
               new Thread(client).start();
          }

          //10000个并发线程瞬间发起请求操作
          signal.countDown();
          finish.await();

          sw.stop();

          String tip = String.format("RPC调用总共耗时: [%s] 毫秒", sw.getTime());
          System.out.println(tip);

          executor.stop();
     }
}
