package design_mode.observer;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author huang_kangjie
 * @date 2018-12-24 15:47
 * @since 1.0.3
 **/
public class Publisher {

     //用于缓存所有的服务器注册信息，当服务器启动的时候，注册进来，掉线后，通知注册中心
     // （因为只是简单的测试观察者模式，所以采用服务器主动通知掉线）
     private static final Map<String, String> cache = new ConcurrentHashMap<>();

     /**
      * 注册服务
      * @param ip   服务器的ip
      */
     public static void register(String ip){
          System.out.println("注册中心正在注册：ip = " + ip);
          cache.put(ip, ip);
          System.out.println("注册成功：ip = " + ip);
     }

     /**
      * 服务器下线，通知其他子服务器
      * @param ip
      */
     public static void shutdown(String ip){
          System.out.println("服务器器掉线：ip = " + ip);
          cache.remove(ip);
          Set<String> ips = cache.keySet();
          Iterator it = ips.iterator();
          while (it.hasNext()) {
               System.out.println("服务器器掉线：ip = " + ip + " ，已通知 ip = " + it.next());
          }
      }

     /**
      * 判断是否还有服务器存活
      * @return
      */
      public static boolean isLive() {
          return cache.keySet().isEmpty();
      }

}
