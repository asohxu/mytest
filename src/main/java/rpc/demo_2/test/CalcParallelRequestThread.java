package rpc.demo_2.test;

/**
 * 并发线程模拟
 * @author huang_kangjie
 * @create 2018-07-11 10:57
 **/

import rpc.demo_2.core.MessageSendExecutor;
import rpc.demo_2.facede.Calculate;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalcParallelRequestThread implements Runnable {

     private CountDownLatch signal;
     private CountDownLatch finish;
     private MessageSendExecutor executor;
     private int taskNumber = 0;

     public CalcParallelRequestThread(MessageSendExecutor executor, CountDownLatch signal, CountDownLatch finish, int taskNumber) {
          this.signal = signal;
          this.finish = finish;
          this.taskNumber = taskNumber;
          this.executor = executor;
     }

     public void run() {
          try {
               signal.await();

               Calculate calc = executor.execute(Calculate.class);
               int add = calc.add(taskNumber, taskNumber);
               System.out.println("calc add result:[" + add + "]");

               finish.countDown();
          } catch (InterruptedException ex) {
               Logger.getLogger(CalcParallelRequestThread.class.getName()).log(Level.SEVERE, null, ex);
          }
     }
}