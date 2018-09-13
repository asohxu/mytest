package generics.test;

import java.util.Arrays;

/**
 * 声明请求入参
 * @author huang_kangjie
 * @create 2018-09-13 16:31
 **/
public class Request {

     private String param;

     private String[] header;

     private String remark;

     @Override
     public String toString() {
          return "Request{" +
                  "param='" + param + '\'' +
                  ", header=" + Arrays.toString(header) +
                  ", remark='" + remark + '\'' +
                  '}';
     }

     public String getParam() {
          return param;
     }

     public void setParam(String param) {
          this.param = param;
     }

     public String[] getHeader() {
          return header;
     }

     public void setHeader(String[] header) {
          this.header = header;
     }

     public String getRemark() {
          return remark;
     }

     public void setRemark(String remark) {
          this.remark = remark;
     }
}
