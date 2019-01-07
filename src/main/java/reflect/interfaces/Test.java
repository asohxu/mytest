package reflect.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author huang_kangjie
 * @date 2019-01-04 14:33
 * @since 1.0.3
 **/
public class Test {

     public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
          Class<?> clazz = Class.forName("reflect.interfaces.DemoServiceImpl2");
          ClassLoader classLoader = clazz.getClassLoader();
          //Class<?> clazz = Class.forName("reflect.interfaces.DemoServiceImpl1");
          //查看该类实现了哪些接口
          Class<?>[] interfaces = clazz.getInterfaces();
          for(Class<?> clz : interfaces) {
               System.out.println("实现了接口： " + clz.getName());
               System.out.println("实现了接口名称： " + clz.getSimpleName());
               DemoServiceImpl2 demo = (DemoServiceImpl2) clazz.newInstance();
               Method[] methods = clz.getMethods();
               for(Method method : methods) {
                    try {
                         //这里有个坑，一开始用Object 来接收的newInstance
                         //会把Object的所有方法都列出来
                         Class<?>[] params = method.getParameterTypes();
                         Object[] parameters = new Object[params.length];
                         for(int i = 0; i < params.length; i ++) {
                              if(params[i].getSimpleName().equals("String")) {
                                   parameters[i] = "param is string";
                              }
                         }
                         method.invoke(demo, parameters);
                    } catch (Exception e) {
                         e.printStackTrace();
                    }
               }
          }

     }

}
