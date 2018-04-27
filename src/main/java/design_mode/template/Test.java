package design_mode.template;

/**
 * @author huang_kangjie
 * @create 2018-04-27 11:16
 **/
public class Test {

     public static void main(String[] args) {

          ClassLoaderTemlate test1 = new LoadTest1();
          test1.load();

          ClassLoaderTemlate test2 = new LoadTest2();
          test2.load();
     }
}
