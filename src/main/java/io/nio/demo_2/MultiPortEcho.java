package io.nio.demo_2;

/**
 * @author huang_kangjie
 * @create 2018-07-13 13:49
 **/
import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class MultiPortEcho {
     private int ports[];
     private ByteBuffer echoBuffer = ByteBuffer.allocate(4);

     public MultiPortEcho(int ports[]) throws IOException {
          this.ports = ports;
          go();
     }

     private void go() throws IOException {
          Selector selector = Selector.open();
          for (int i = 0; i < ports.length; ++i) {
               ServerSocketChannel ssc = ServerSocketChannel.open();
               ssc.configureBlocking(false);
               ServerSocket ss = ssc.socket();
               InetSocketAddress address = new InetSocketAddress(ports[i]);
               ss.bind(address);
               SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);
               System.out.println("Going to listen on " + ports[i]);
          }

          while (true) {
               int num = selector.select();
               Set selectedKeys = selector.selectedKeys();
               Iterator it = selectedKeys.iterator();
               while (it.hasNext()) {
                    SelectionKey key = (SelectionKey) it.next();
                    if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                         ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                         SocketChannel sc = ssc.accept();
                         sc.configureBlocking(false);
                         SelectionKey newKey = sc.register(selector, SelectionKey.OP_READ);
                         it.remove();
                         System.out.println("Got connection from " + sc);
                    } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                         SocketChannel sc = (SocketChannel) key.channel();
                         int bytesEchoed = 0;
                         StringBuilder sb = new StringBuilder();
                         while (true) {
                              echoBuffer.clear();
                              int r = sc.read(echoBuffer);
                              if (r <= 0) {
                                   sc.close();
                                   break;
                              }
                              echoBuffer.flip();

                              ByteBuffer rs = ByteBuffer.allocate(1024);
                              rs.put("this is a test".getBytes());
                              rs.flip();

                              sc.write(rs);
                              bytesEchoed += r;
                              String msg = new String(echoBuffer.array(), 0, echoBuffer.position());
                              sb.append(msg);
                         }
                         System.out.println("receive msg : " + sb.toString());

                         System.out.println("Echoed " + bytesEchoed + " from " + sc);
                         it.remove();
                    }

               }
               // System.out.println( "going to clear" );
               // selectedKeys.clear();
               // System.out.println( "cleared" );
          }
     }

     static public void main(String args[]) throws Exception {
          int ports[] = new int[] { 9527 };
          for (int i = 0; i < args.length; ++i) {
               ports[i] = Integer.parseInt(args[i]);
          }
          new MultiPortEcho(ports);
     }
}
