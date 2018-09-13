package generics.test;

import java.util.Random;

/**
 *
 * 根据泛型接口实现了自己想要的返回类型
 * @author huang_kangjie
 * @create 2018-09-13 16:35
 **/
public class QueryServiceImpl implements QueryService<Request, Respone> {

     @Override
     public Respone query(Request request) {
          System.out.println("请求消息体: request = " + request.toString());

          Integer random = new Random().nextInt(Integer.MAX_VALUE);

          Respone respone = new Respone();
          respone.setCode("" + random);
          respone.setData("返回的data = " + random);
          respone.setMsg("返回的msg " + random);

          return respone;
     }

}
