package excl.poi.util.test;

import excl.poi.util.ExcelUtil;
import excl.poi.util.ExclTemplate;
import excl.poi.util.StudentVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huang_kangjie
 * @create 2018-04-27 16:58
 **/
public class TemplateTest extends ExclTemplate{

     @Override
     public List<?> buildExclData() {
          List<StudentVO> list = new ArrayList<StudentVO>();
          for (int i = 1; i <= 1000; i++) {
               StudentVO studentVO = new StudentVO();
               studentVO.setId(i);
               studentVO.setName("张三" + i);
               studentVO.setAge(26);
               studentVO.setClazz("五期提高班");
               studentVO.setCompany("天融信");
               list.add(studentVO);
          }
          return list;
     }

     @Test
     public void tests(){
          ExcelUtil<StudentVO> util = new ExcelUtil<StudentVO>(StudentVO.class);
          //导出返回的的一个工作簿
          HSSFWorkbook wb = exportExcel(util, "学生工作簿");
          try {
               FileOutputStream fout = new FileOutputStream("D:\\test.xls");
               wb.write(fout);
               fout.close();
          } catch (IOException e) {
               e.printStackTrace();
          }
          System.out.println("导出完成！！！");
     }
}
