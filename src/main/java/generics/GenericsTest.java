package generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型测试
 * @author huang_kangjie
 * @create 2018-09-13 15:06
 **/
public class GenericsTest {

     /**
      * 为什么需要泛型？
      * 一下列子就是证明泛型的重要性
      *
      * 会抛出java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
      */
     @Test
     public void test1(){
          List arrayList = new ArrayList();
          arrayList.add("aaaa");
          arrayList.add(100);

          for(int i = 0; i< arrayList.size();i++){
               String item = (String)arrayList.get(i);
               System.out.println("泛型测试 item = " + item);
          }
     }

     /**
      * 泛型有三种使用方式，分别为：泛型类、泛型接口、泛型方法
      */

}
