package thread.futuer;

/**
 * 用于接收线程执行的返回结果
 * @author huang_kangjie
 * @create 2018-07-03 14:12
 **/
public class BackInfoBean {

     /**
      * 错误码
      */
     private int code;

     /**
      * 错误信息
      */
     private String msg;
     

     public int getCode() {
          return code;
     }

     public void setCode(int code) {
          this.code = code;
     }

     public String getMsg() {
          return msg;
     }

     public void setMsg(String msg) {
          this.msg = msg;
     }
}
