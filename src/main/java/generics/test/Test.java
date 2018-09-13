package generics.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huang_kangjie
 * @create 2018-09-13 16:42
 **/
public class Test {

     public static void main(String[] args) {
          Request request = new Request();
          request.setHeader(new String[]{"header1","header2"});
          request.setParam("this is request param !");
          request.setRemark("标签");

          QueryService<Request, Respone> query = new QueryServiceImpl();
          Respone respone = query.query(request);
          System.out.println(respone.toString());

          System.out.println("---------------------------------");

          Map<String, Object> param = new HashMap<>();
          param.put("参数1", "123");
          param.put("参数2", "121ttt23");
          QueryService<Object, Object> object = new QueryServiceObjectImpl();
          Object objectRs = object.query(param);
          System.out.println(objectRs);

     }
}
