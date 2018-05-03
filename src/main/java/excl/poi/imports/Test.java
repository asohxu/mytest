package excl.poi.imports;

import excl.poi.ExcelUtil;

import java.util.List;

/**
 * @author huang_kangjie
 * @create 2018-05-02 15:41
 **/
public class Test {

     @org.junit.Test
     public void readTest(){
          String destFile = "D://Test.xls";

          ExcelUtil ex = new ExcelUtil();
          List<List<String>> list= ex.read(destFile);
          System.out.println(list);
     }
}
