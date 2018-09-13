package generics.test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 根据泛型接口实现了自己想要的返回类型
 * @author huang_kangjie
 * @create 2018-09-13 16:35
 **/
public class QueryServiceObjectImpl implements QueryService<Object, Object> {


     @Override
     public Object query(Object request) {
          System.out.println("收到请求： " + request);
          Map<String, Object> rs = new HashMap<>();
          rs.put("adsf", "123");
          rs.put("aqweqweqwedsf", "121ttt23");
          return rs;
     }
}
