package rpc.demo_2.core;

/**
 * Rpc客户端线程任务处理
 * @author huang_kangjie
 * @create 2018-07-11 10:45
 **/
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import rpc.demo_2.serialize.RpcSerializeProtocol;

import java.net.InetSocketAddress;

public class MessageSendInitializeTask implements Runnable {

     private EventLoopGroup eventLoopGroup = null;
     private InetSocketAddress serverAddress = null;
     private RpcServerLoader loader = null;

     MessageSendInitializeTask(EventLoopGroup eventLoopGroup, InetSocketAddress serverAddress, RpcServerLoader loader) {
          this.eventLoopGroup = eventLoopGroup;
          this.serverAddress = serverAddress;
          this.loader = loader;
     }

     MessageSendInitializeTask(EventLoopGroup eventLoopGroup, InetSocketAddress serverAddress, RpcServerLoader loader, RpcSerializeProtocol serializeProtocol) {
          this.eventLoopGroup = eventLoopGroup;
          this.serverAddress = serverAddress;
          this.loader = loader;
     }

     public void run() {
          Bootstrap b = new Bootstrap();
          b.group(eventLoopGroup)
                  .channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true);
          b.handler(new MessageSendChannelInitializer());

          ChannelFuture channelFuture = b.connect(serverAddress);
          channelFuture.addListener(new ChannelFutureListener() {
               public void operationComplete(final ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                         MessageSendHandler handler = channelFuture.channel().pipeline().get(MessageSendHandler.class);
                         MessageSendInitializeTask.this.loader.setMessageSendHandler(handler);
                    }
               }
          });
     }
}
