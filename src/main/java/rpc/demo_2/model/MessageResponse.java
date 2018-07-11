package rpc.demo_2.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
/**
 * RPC应答消息结构
 * @author huang_kangjie
 * @create 2018-07-11 10:37
 **/
public class MessageResponse implements Serializable {

     private String messageId;
     private String error;
     private Object resultDesc;

     public String getMessageId() {
          return messageId;
     }

     public void setMessageId(String messageId) {
          this.messageId = messageId;
     }

     public String getError() {
          return error;
     }

     public void setError(String error) {
          this.error = error;
     }

     public Object getResult() {
          return resultDesc;
     }

     public void setResult(Object resultDesc) {
          this.resultDesc = resultDesc;
     }

     public String toString() {
          return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                  .append("messageId", messageId).append("error", error).toString();
     }
}