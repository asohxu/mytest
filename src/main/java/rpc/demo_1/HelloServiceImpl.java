package rpc.demo_1;

/**
 * HelloServices接口实现类
 * @author huang_kangjie
 * @create 2018-07-09 14:54
 **/
public class HelloServiceImpl implements HelloService {

     public String sayHi(String name) {
          return "Hi, " + name;
     }

}