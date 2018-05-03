package excl.poi.export;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * excl导出的模板
 * 采用末班模式，子类只需要实现需要导出的内容即可
 * @author huang_kangjie
 * @create 2018-04-27 15:26
 **/
public abstract class ExclTemplate {

     private String fileName;

     /**
      * 返回一个工作簿
      * @param excelUtil
      * @param fileName
      * @return
      */
     public HSSFWorkbook exportExcel(ExcelUtil<?> excelUtil, String fileName){
          this.fileName = fileName;
          return excelUtil.exportExcel(buildExclData(), fileName);
     }

     /**
      * 用于浏览器直接导出
      * @param response
      * @param excelUtil
      * @param fileName
      * @throws IOException
      */
     public void exportExcel(HttpServletResponse response, ExcelUtil<?> excelUtil, String fileName) throws IOException{
          this.fileName = fileName;
          HSSFWorkbook workbook = excelUtil.exportExcel(buildExclData(), fileName);
          export(response, workbook);
     }

     /**
      * 构建excl需要导出的数据
      * @return
      */
     public abstract List<?> buildExclData();

     /**
      * 导出excel
      * @param response
      * @param workbook
      * @throws IOException
      * @throws UnsupportedEncodingException
      */
     protected void export(HttpServletResponse response, HSSFWorkbook workbook) throws IOException{
          // 设置响应头导出文件格式
          String contentDisposition = new StringBuilder("attachment; filename=").append(new String((fileName + ".xls").getBytes("utf-8"), "ISO-8859-1")).toString();
          // 设置响应头的文件名称信息
          response.setHeader("Content-disposition", contentDisposition);
          // 设置响应头导出文件格式
          response.setContentType("application/vnd.ms-excel");
          response.setCharacterEncoding("UTF-8");

          OutputStream output = response.getOutputStream();// 取得输出流
          output.flush();
          workbook.write(output);
          output.close();
     }

}
