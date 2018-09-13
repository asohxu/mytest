package generics.clazz;

/**
 * 泛型类 - 例子
 * @author huang_kangjie
 * @create 2018-09-13 15:18
 **/
//此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
//在实例化泛型类时，必须指定T的具体类型
public class GenericsClazz<T> {
     //key这个成员变量的类型为T,T的类型由外部指定
     private T key;

     //泛型构造方法形参key的类型也为T，T的类型由外部指定
     public GenericsClazz(T key) {
          this.key = key;
     }

     public T getKey(){ //泛型方法getKey的返回值类型为T，T的类型由外部指定
          if(key instanceof String){
               System.out.print("泛型指定的是string类型的，");
          }
          return key;
     }


}
