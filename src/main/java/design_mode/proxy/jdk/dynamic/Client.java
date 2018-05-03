package design_mode.proxy.jdk.dynamic;

import design_mode.proxy.jdk.dynamic.interfaces.ClassA;
import design_mode.proxy.jdk.dynamic.interfaces.UserManager;
import design_mode.proxy.jdk.dynamic.interfaces.UserManagerImpl;

/**
 * @author huang_kangjie
 * @create 2018-05-03 16:17
 **/
public class Client {

     /**
      * @param args
      */
     public static void main(String[] args) {

          ProxyHandler proxyHandler = new ProxyHandler();
          UserManager userManager = (UserManager)proxyHandler.newProxyInstance(new UserManagerImpl());

          String name = userManager.findUser("0001");
          System.out.println("client.main-->>" + name);

          userManager.testManyParams("this is first param", 2);

          /**
           * jdk的动态代理只能是接口，不能代理某个类
           */
          try {
               ClassA classA = (ClassA) proxyHandler.newProxyInstance(new ClassA());
               classA.print();
          } catch (Exception e) {
               e.printStackTrace();
          }
     }
}
