package rpc.demo_2.core;

/**
 * Rpc客户端执行模块
 * @author huang_kangjie
 * @create 2018-07-11 10:42
 **/
import rpc.demo_2.serialize.RpcSerializeProtocol;

import java.lang.reflect.Proxy;

public class MessageSendExecutor {

     private RpcServerLoader loader = RpcServerLoader.getInstance();
     private RpcSerializeProtocol serializeProtocol;

     /**
      * 消息发送执行器构造方法
      * 1、启动一个客户端的时候把服务器地址加载好
      * @param serverAddress
      */
     public MessageSendExecutor(String serverAddress) {
          loader.load(serverAddress, serializeProtocol);
     }

     public void stop() {
          loader.unLoad();
     }

     public static <T> T execute(Class<T> rpcInterface) {
          return (T) Proxy.newProxyInstance(
                  rpcInterface.getClassLoader(),
                  new Class<?>[]{rpcInterface},
                  new MessageSendProxy<T>(rpcInterface)
          );
     }
}
