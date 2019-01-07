package reflect.interfaces;

/**
 * @author huang_kangjie
 * @date 2019-01-04 14:35
 * @since 1.0.3
 **/
public class DemoServiceImpl2 implements IDemoService, IDemeService2 {

     @Override
     public void pring(String name) {
          System.out.println(" 实现类2 DemoServiceImpl2 IDemoService print() : " + name);
     }

     @Override
     public void print2(String name, int s2) {
          System.out.println(" 实现类2 DemoServiceImpl2 IDemeService2 print() : " + name);
          System.out.println(" 实现类2 DemoServiceImpl2 IDemeService2 print() : " + s2);
     }

}