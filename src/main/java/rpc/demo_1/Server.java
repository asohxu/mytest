package rpc.demo_1;

import java.io.IOException;

/**
 * 服务中心
 * @author huang_kangjie
 * @create 2018-07-09 14:55
 **/
 interface Server {
      void stop();

      void start() throws IOException;

      void register(Class serviceInterface, Class impl);

      boolean isRunning();

      int getPort();
}
