package design_mode.proxy.jdk.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 采用JDK动态代理必须实现InvocationHandler接口，采用Proxy类创建相应的代理类
 * @author huang_kangjie
 * @create 2018-05-03 16:10
 **/
public class ProxyHandler implements InvocationHandler{

     private Object targetObject;

     /**
      * 目标的初始化方法，根据目标生成代理类
      * @param targetObject
      * @return
      */
     public Object newProxyInstance(Object targetObject) {
          this.targetObject = targetObject;
          //第一个参数，目标的装载器
          //第二个参数，目标接口，为每个接口生成代理
          //第三个参数，调用实现了InvocationHandler的对象，当你一调用代理，代理就会调用InvocationHandler的invoke方法
          return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                  targetObject.getClass().getInterfaces(),this);
     }



     @Override
     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          //记录日志等操作或打印输入参数
          System.out.println("start-->>" + method.getName());
          for(int i=0;i<args.length;i++){
               //打印调用目标方法的参数
               System.out.println(args[i]);
          }
          Object ret = null;
          System.out.println("代理前：>>>>>>>>>>>>>>>>>>>>> ");
          try{
               //调用目标方法
               ret = method.invoke(targetObject, args);
               //执行成功，打印成功信息
               System.out.println("success-->>" + method.getName());
          }catch(Exception e){
               e.printStackTrace();
               //失败时，打印失败信息
               System.out.println("error-->>" + method.getName());
               throw e;
          }
          System.out.println(">>>>>>>>>>>>>>>>>>>>> 代理后 ");
          return ret;
     }

}
