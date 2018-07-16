package io.nio.demo_2;

/**
 * @author huang_kangjie
 * @create 2018-07-13 13:51
 **/
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
     public static void main(String[] args) throws IOException {
          SocketChannel socketChannel = SocketChannel.open();
          SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 9527);
          socketChannel.connect(socketAddress);
          String str = "你好a啊飒飒大法师";
          ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
          socketChannel.write(buffer);
          socketChannel.socket().shutdownOutput();

          buffer.clear();
          byte[] bytes;
          int count = 0;
          while ((count = socketChannel.read(buffer)) > 0) {
               buffer.flip();
               bytes = new byte[count];
               buffer.get(bytes);
               System.out.println(new String(buffer.array()));
               buffer.clear();
          }
          socketChannel.socket().shutdownInput();
          socketChannel.socket().close();
          socketChannel.close();
     }
}
