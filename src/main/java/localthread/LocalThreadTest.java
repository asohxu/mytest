package localthread;

/**
 *
 * @author huang_kangjie
 * @create 2018-07-02 15:16
 **/
public class LocalThreadTest {

     public static void main(String[] args) {

          new Thread(new IntegerTask("IntegerTask-1")).start();
          new Thread(new IntegerTask("IntegerTask-2")).start();
          new Thread(new IntegerTask("IntegerTask-3")).start();
          new Thread(new StringTask("StringTask-1")).start();
          new Thread(new StringTask("StringTask-2")).start();
          new Thread(new StringTask("StringTask-3")).start();
     }

}
