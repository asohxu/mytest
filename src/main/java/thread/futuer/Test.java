package thread.futuer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author huang_kangjie
 * @create 2018-07-03 14:26
 **/
public class Test {

     public static void main(String[] args) throws ExecutionException, InterruptedException {
          ////新建了一个固定线程池
          //ExecutorService service = Executors.newFixedThreadPool(10);
          ////用于提交一组Callable任务，其take方法返回已完成的一个Callable任务对应的Future对象。
          //CompletionService<BackInfoBean> completionService = new ExecutorCompletionService<>(service);
          //for (int i = 0; i < 10; i++) {
          //     Callable taskWorker = new TaskWorker("thread-"+i);
          //     completionService.submit(taskWorker);
          //}
          //
          //System.out.println(">>>>>>>>>>>>>>>> 线程提交完毕");
          ////获取任务的结果
          //for(int j=0;j<10;j++){
          //     Future<BackInfoBean> future =completionService.take();
          //     System.out.println(future.get().getMsg());
          //}
          //System.out.println(">>>>>>>>>>>>>>>> 任务执行完毕");
          ////关闭线程池
          //service.shutdown();

          //-----------------------------测试CompletionService是谁返回就直接返回谁的信息-------------------------------------------
          //新建了一个固定线程池
          ExecutorService service = Executors.newFixedThreadPool(10);
          //用于提交一组Callable任务，其take方法返回已完成的一个Callable任务对应的Future对象。
          List<Future<BackInfoBean>> list = new ArrayList<>();
          for (int i = 0; i < 10; i++) {
               Callable taskWorker = new TaskWorker("thread-"+i);
               Future future = service.submit(taskWorker);
               list.add(future);
          }
          System.out.println(">>>>>>>>>>>>>>>> 线程提交完毕");
          //获取任务的结果
          //用list集合存放数据，缺点就是等待，等待所有的所属顺序的Futuer执行完毕后，再调用下一个futuer的get方法
          //for(int j=0;j<10;j++){
          //     BackInfoBean future = list.get(j).get();
          //     System.out.println(future.getMsg());
          //}

          //循环判断获取
          int count = 0;
          int init = list.size();
          while (true) {
               if(count == init) {
                    break;
               }
               for(int j=0;j<list.size();j++){
                    if(list.get(j).isDone() && !list.get(j).isCancelled()) {
                         BackInfoBean future = list.get(j).get();
                         System.out.println(future.getMsg());
                         count++;
                         list.remove(j);
                    } else {
                         //System.err.println("thread-" + j + "还没执行完毕。。。");
                    }
                    Thread.sleep(5);//避免CPU高速运转，这里休息1毫秒，CPU纳秒级别
               }
          }
          System.out.println(">>>>>>>>>>>>>>>> 任务执行完毕");
          //关闭线程池
          service.shutdown();

     }


}
