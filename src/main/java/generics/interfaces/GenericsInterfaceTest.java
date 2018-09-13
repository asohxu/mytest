package generics.interfaces;

/**
 * @author huang_kangjie
 * @create 2018-09-13 15:29
 **/
public class GenericsInterfaceTest {

     public static void main(String[] args) {
          GenerficInterface gstring = new GenerficStringImpl();
          System.out.println(gstring.next());

          GenerficIntgerImpl gintger = new GenerficIntgerImpl();
          System.out.println(gintger.next());
     }
}
