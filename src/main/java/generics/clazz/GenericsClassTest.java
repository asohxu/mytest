package generics.clazz;

/**
 *
 * 泛型类型用于类的定义中，被称为泛型类。通过泛型可以完成对一组类的操作对外开放相同的接口。
 * 最典型的就是各种容器类，如：List、Set、Map。
 * @author huang_kangjie
 * @create 2018-09-13 15:17
 **/
public class GenericsClassTest {
  //   写法
  //   class 类名称 <泛型标识：可以随便写任意标识号，标识指定的泛型的类型>{
  //        private 泛型标识 /*（成员变量类型）*/ var;
  //     .....
  //
  //   }


     public static void main(String[] args) {
          //泛型为string
          GenericsClazz<String> string = new GenericsClazz<>("string");
          System.out.println("泛型测试，输入的key = " + string.getKey());

          //泛型为Intger
          GenericsClazz<Integer> intger = new GenericsClazz<>(123);
          System.out.println("泛型测试，输入的key = " + intger.getKey());

          //定义的泛型类，声明的泛型可以不用泛型实参
          GenericsClazz empty = new GenericsClazz(123);
          GenericsClazz empty2 = new GenericsClazz("123");
          System.out.println("泛型测试，输入的key = " + empty.getKey());
          System.out.println("泛型测试，输入的key = " + empty2.getKey());
     }

}
