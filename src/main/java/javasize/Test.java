package javasize;

import com.carrotsearch.sizeof.RamUsageEstimator;

/**
 * @author huang_kangjie
 * @create 2018-06-01 16:03
 **/
public class Test {
     public static void main(String[] args) {
          System.out.println("count  = " + RamUsageEstimator.sizeOf(new NewObject().count));;
          System.out.println("flag  = " + RamUsageEstimator.sizeOf(new NewObject().flag));;
          System.out.println("ob  = " + RamUsageEstimator.sizeOf(new NewObject().ob));;
          System.out.println("NewObject  = " + RamUsageEstimator.sizeOf(new NewObject()));;
     }
}
