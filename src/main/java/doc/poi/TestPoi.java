package doc.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huang_kangjie
 * @create 2018-08-15 15:43
 **/
public class TestPoi {
     public static void main(String[] args) throws IOException {
          Map<String, Object> param=new HashMap<String, Object>();
          param.put("${name}", "Joanna.Yan");
          param.put("${examNum}", "000000000001");
          param.put("${IDCard}", "111111111111111111");
          param.put("${carModel}", "C1");
          CustomXWPFDocument doc=WordUtil.generateWord(param, "D:\\joanna.docx");
          FileOutputStream fopts = new FileOutputStream("D:\\yan.docx");
          doc.write(fopts);
          fopts.close();
     }
}
