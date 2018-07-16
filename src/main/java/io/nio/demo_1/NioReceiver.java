package io.nio.demo_1;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * nio服务器端 - 只能接受消息的服务端
 *
 * 主要是为了学习nio中selector的作用
 * @author huang_kangjie
 * @create 2018-07-13 10:30
 **/
public class NioReceiver {

     public static void main(String[] args) throws  Exception {
          //创建一个buffer,允许最大接收字节8
          ByteBuffer buffer = ByteBuffer.allocate(8);
          //初始化一个ServerSocket通道
          ServerSocketChannel ssc = ServerSocketChannel.open();
          //初始化一个Selecter
          Selector selector = Selector.open();
          //设置通道为非阻塞通道
          ssc.configureBlocking(false);
          //初始化一个ServerSocket
          ServerSocket server = ssc.socket();
          //绑定端口
          InetSocketAddress address = new InetSocketAddress(9527);
          server.bind(address);
          //重点：注册到selector
          SelectionKey skey = ssc.register(selector, SelectionKey.OP_ACCEPT);


          //以上步骤，创建好一个服务端
          System.out.println("开始监听");

          while (true) {
               //返回当前已连接好的通道数
               int num = selector.select();
               //获取所有可用用的通道，并遍历他们
               Set selectKeys = selector.selectedKeys();
               Iterator it = selectKeys.iterator();
               while (it.hasNext()) {
                    SelectionKey key = (SelectionKey) it.next();
                    SocketChannel channel = null;
                    if(key.isAcceptable()) {
                         //当前客户端通道已经准备好连接，等待服务器创建通道
                         //通过key拿到服务端通道
                         ServerSocketChannel sc = (ServerSocketChannel) key.channel();
                         //通过服务端通道获取一个客户端
                         channel = sc.accept();
                         channel.configureBlocking(false);
                         //把客户端的状态更改为可读
                         channel.register(selector, SelectionKey.OP_READ);
                         it.remove();
                    } else if (key.isReadable()) {
                         channel = (SocketChannel) key.channel();
                         StringBuilder sb = new StringBuilder();
                         while (true) {
                              buffer.clear();
                              int r = channel.read(buffer);
                              if (r <= 0) {
                                   channel.close();
                                   System.out.println("接收完毕，断开连接");
                                   break;
                              }
                              String msg = new String(buffer.array(), 0, buffer.position());
                              //System.out.println("##" + r + " " + msg);
                              sb.append(msg);
                              buffer.flip();
                         }
                         System.out.println("request = " + sb.toString());
                         it.remove();
                    } else {
                         channel.close();
                    }
               }
          }


     }
}
