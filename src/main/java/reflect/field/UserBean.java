package reflect.field;

import excl.poi.export.ExcelVOAttribute;

/**
 *
 * @author huang_kangjie
 * @create 2018-04-27 14:41
 **/
public class UserBean {

     public int num1 = 1;
     protected int num2 = 2;
     String num3 = "3";
     private int num4 = 4;
     private String num5 = "5";
     @ExcelVOAttribute(
             name = "this is a test", column = "A",
             combo = {"asdf-1","adsf-2"},
             isExport = false, prompt = "提示个蛋！！！", width = 500)
     private Integer num6 = 6;

     public void print(){
          System.out.println(">>>>>>>>>>>> this is print();");
     }

     public int getNum4() {
          return num4;
     }

     public void setNum4(int num4) {
          this.num4 = num4;
     }

     public String getNum5() {
          return num5;
     }

     public void setNum5(String num5) {
          this.num5 = num5;
     }

     public Integer getNum6() {
          return num6;
     }

     public void setNum6(Integer num6) {
          this.num6 = num6;
     }
}
