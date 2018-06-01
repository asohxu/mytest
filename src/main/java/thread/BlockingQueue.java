package test.thread;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 类说明
 *
 * @author 作者 : huang_kangjie
 * @version 创建时间：2017年3月3日
 * 
 */


public class BlockingQueue {
    
    public static void main(String[] args){
        //定义一个线程共享的队列容器，可以使得数据由队列的一端输入，从另外一端输出
        final ArrayBlockingQueue<String> queue=new ArrayBlockingQueue<String>(16);
//        final BlockingQueue<String> queue=new ArrayBlockingQueue<String>(16);
        for(int i=0;i<4;i++){
            new Thread(new Runnable(){

                @Override
                public void run() {
                    while(true){
                        try {
                            parseLog(queue.take());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                
            }).start();
        }
        
        System.out.println("begin:"+(System.currentTimeMillis()/1000));
        /*模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。
        修改程序代码，开四个线程让这16个对象在4秒钟打完。
        */
        for(int i=0;i<16;i++){  //这行代码不能改动
            final String log = ""+(i+1);//这行代码不能改动
            {
                     try {
                        queue.put(log);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
    
    //parseLog方法内部的代码不能改动
    public static void parseLog(String log){
        System.out.println(log+":"+(System.currentTimeMillis()/1000));
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }        
    }
    
}
