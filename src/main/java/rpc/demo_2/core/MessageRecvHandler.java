package rpc.demo_2.core;

/**
 * Rpc服务器消息处理
 * @author huang_kangjie
 * @create 2018-07-11 10:48
 **/
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.Map;
import rpc.demo_2.model.MessageRequest;
import rpc.demo_2.model.MessageResponse;

public class MessageRecvHandler extends ChannelInboundHandlerAdapter {

     private final Map<String, Object> handlerMap;

     public MessageRecvHandler(Map<String, Object> handlerMap) {
          this.handlerMap = handlerMap;
     }

     public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
          MessageRequest request = (MessageRequest) msg;
          MessageResponse response = new MessageResponse();
          MessageRecvInitializeTask recvTask = new MessageRecvInitializeTask(request, response, handlerMap, ctx);
          //不要阻塞nio线程，复杂的业务逻辑丢给专门的线程池
          MessageRecvExecutor.submit(recvTask);
     }

     public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
          //网络有异常要关闭通道
          ctx.close();
     }
}
