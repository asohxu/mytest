package design_mode.proxy.cglib;

import design_mode.proxy.jdk.dynamic.interfaces.UserManager;
import design_mode.proxy.jdk.dynamic.interfaces.UserManagerImpl;

/**
 * @author huang_kangjie
 * @create 2018-05-03 17:02
 **/
public class Test {

     public static void main(String[] args) {
          //cglib动态代理可以代理某个类、也可以代理接口
          //jdk动态代理只能代理接口的实现类
          DynamicProxy dynamicProxy = new DynamicProxy();
          ClassA classA = (ClassA) dynamicProxy.getProxyObject(new ClassA());
          classA.print();

          UserManager user = (UserManager) dynamicProxy.getProxyObject(new UserManagerImpl());
          String rs = user.findUser("test");
          System.out.println("代理接口实现返回 ： "  + rs);
     }
}
