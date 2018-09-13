package generics.method;

/**
 * @author huang_kangjie
 * @create 2018-09-13 15:41
 **/
public class Test {

     public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {


          GenericsMethod method = new GenericsMethod();
          MethodTest test = method.genericMethod(MethodTest.class);
          test.print();

     }
}
