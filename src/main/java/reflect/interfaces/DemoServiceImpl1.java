package reflect.interfaces;

/**
 * @author huang_kangjie
 * @date 2019-01-04 14:34
 * @since 1.0.3
 **/
public class DemoServiceImpl1 implements IDemoService {

     @Override
     public void pring(String name) {
          System.out.println(" 实现类1 DemoServiceImpl1 IDemoService() print : " + name);
     }
}
