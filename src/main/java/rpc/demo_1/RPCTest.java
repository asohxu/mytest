package rpc.demo_1;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * 测试类
 * @author huang_kangjie
 * @create 2018-07-09 14:58
 **/
public class RPCTest {
     public static void main(String[] args) throws IOException {
          new Thread(new Runnable() {
               public void run() {
                    try {
                         Server serviceServer = new ServiceCenter(8088);
                         serviceServer.register(HelloService.class, HelloServiceImpl.class);
                         serviceServer.start();
                    } catch (IOException e) {
                         e.printStackTrace();
                    }
               }
          }).start();
          HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
          System.out.println(service.sayHi("test"));
     }
}
