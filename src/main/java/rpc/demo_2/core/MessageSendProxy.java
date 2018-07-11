package rpc.demo_2.core;

/**
 * Rpc客户端消息处理
 *
 * 这里的RPC客户端实际上，是动态代理了MessageSendProxy，
 * 当然这里是应用了，JDK原生的动态代理实现，你还可以改成CGLIB（Code Generation Library）方式。
 * 不过本人测试了一下CGLIB方式，在高并发的情况下面会出现空指针异常，但是同样的情况，
 * JDK原生的动态代理却没有问题。并发程度不高的情况下面，两种代理方式都运行正常。
 * 后续再深入研究看看吧！废话不说了，现在给出MessageSendProxy的实现方式
 * @author huang_kangjie
 * @create 2018-07-11 10:42
 **/
import rpc.demo_2.model.MessageRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

public class MessageSendProxy<T> implements InvocationHandler {

     private Class<T> cls;

     public MessageSendProxy(Class<T> cls) {
          this.cls = cls;
     }

     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          MessageRequest request = new MessageRequest();
          request.setMessageId(UUID.randomUUID().toString());
          request.setClassName(method.getDeclaringClass().getName());
          request.setMethodName(method.getName());
          request.setTypeParameters(method.getParameterTypes());
          request.setParameters(args);

          MessageSendHandler handler = RpcServerLoader.getInstance().getMessageSendHandler();
          MessageCallBack callBack = handler.sendRequest(request);
          return callBack.start();
     }
}
