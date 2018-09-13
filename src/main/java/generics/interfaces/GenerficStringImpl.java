package generics.interfaces;

/**
 * @author huang_kangjie
 * @create 2018-09-13 15:31
 **/
public class GenerficStringImpl implements GenerficInterface<String>{

     @Override
     public String next() {
          return "我是string";
     }
}
