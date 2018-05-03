package design_mode.proxy.jdk.dynamic.interfaces;

/**
 * @author huang_kangjie
 * @create 2018-05-03 16:18
 **/
public class UserManagerImpl implements UserManager{
     @Override
     public String findUser(String id) {
          return "根据id = " + id + "查找到用户信息";
     }

     @Override
     public void testManyParams(String s1, int s2) {
          System.out.println("testManyParams method : params s1 = " + s1 + "; s2 = " + s2);
     }
}
