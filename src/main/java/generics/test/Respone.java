package generics.test;

/**
 * @author huang_kangjie
 * @create 2018-09-13 16:33
 **/
public class Respone {

     private String msg;

     private String code;

     private Object data;

     @Override
     public String toString() {
          return "Respone{" +
                  "msg='" + msg + '\'' +
                  ", code='" + code + '\'' +
                  ", data=" + data +
                  '}';
     }

     public String getMsg() {
          return msg;
     }

     public void setMsg(String msg) {
          this.msg = msg;
     }

     public String getCode() {
          return code;
     }

     public void setCode(String code) {
          this.code = code;
     }

     public Object getData() {
          return data;
     }

     public void setData(Object data) {
          this.data = data;
     }
}
