package design_mode.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * 动态代理类
 * 实现了一个方法拦截器接口
 * @author huang_kangjie
 * @create 2018-05-03 16:56
 **/
public class DynamicProxy implements MethodInterceptor {

     // 被代理对象
     Object targetObject;

     //Generate a new class if necessary and uses the specified callbacks (if any) to create a new object instance.
     //Uses the no-arg constructor of the superclass.
     //动态生成一个新的类，使用父类的无参构造方法创建一个指定了特定回调的代理实例
     public Object getProxyObject(Object object) {
          this.targetObject = object;
          //增强器，动态代码生成器
          Enhancer enhancer=new Enhancer();
          //回调方法
          enhancer.setCallback(this);
          //设置生成类的父类类型
          enhancer.setSuperclass(targetObject.getClass());
          //动态生成字节码并返回代理对象
          return enhancer.create();
     }

     /**
      参数Object object是被代理对象，不会出现死循环的问题。
      参数java.lang.reflect.Method method是java.lang.reflect.Method类型的被拦截方法。
      参数Object[] args是被被拦截方法的参数。
      参数MethodProxy proxy是CGLIB提供的MethodProxy 类型的被拦截方法。
      */
     // 拦截方法
     @Override
     public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
          // 被织入的横切内容，开始时间 before
          long start = System.currentTimeMillis();
          lazy();

          System.out.println("代理前>>>>>>>>>>>>>>>>>>>>>>");

          // 调用方法
          Object result = methodProxy.invoke(targetObject, args);


          System.out.println(">>>>>>>>>>>>>>>>>>>>>>代理后");

          // 被织入的横切内容，结束时间
          Long span = System.currentTimeMillis() - start;
          System.out.println("共用时：" + span);

          return result;
     }

     // 模拟延时
     public void lazy() {
          try {
               int n = (int) new Random().nextInt(500);
               Thread.sleep(n);
          } catch (InterruptedException e) {
               e.printStackTrace();
          }
     }

}
