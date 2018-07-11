package rpc.demo_2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author huang_kangjie
 * @create 2018-07-11 10:54
 **/
public class RpcServerBootstrap {
     public static void main(String[] args) {
          ClassPathXmlApplicationContext context =  new ClassPathXmlApplicationContext("classpath:rpc-invoke-config.xml");
          context.start();
     }
}
