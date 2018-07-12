package rpc.demo_2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动服务端：
 * 1、扫描服务器xml
 * 2、配置facade接口和对应的实现类，有点像dubbo的配置文件，把所有的接口和接口暴露方法、具体实现类全部在启动服务器的时候加载好
 * 3、默认启动服务端
 * @author huang_kangjie
 * @create 2018-07-11 10:54
 **/
public class RpcServerBootstrap {

     public static void main(String[] args) {
          //手动加载xml文件
          ClassPathXmlApplicationContext context =  new ClassPathXmlApplicationContext("classpath:rpc-invoke-config.xml");
          context.start();
     }
}
