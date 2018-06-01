package excl.insert_img;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author huang_kangjie
 * @create 2018-05-17 14:27
 **/
public class ExcelImageTest {
     public static void main(String[] args) {
          FileOutputStream fileOut = null;
          BufferedImage bufferImg = null;
          //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
          try {

               HSSFWorkbook wb = new HSSFWorkbook();
               HSSFSheet sheet1 = wb.createSheet("test picture");
               // 插入 PNG 图片至 Excel
               String fileName = "D:/77.jpg";

               InputStream is = new FileInputStream(fileName);
               byte[] bytes = IOUtils.toByteArray(is);

               int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);

               CreationHelper helper = wb.getCreationHelper();
               Drawing drawing = sheet1.createDrawingPatriarch();
               ClientAnchor anchor = helper.createClientAnchor();

               HSSFRow row = sheet1.createRow(3);
               HSSFCell cell_0 = row.createCell(4);
               row.setHeight((short) 2000);

               // 图片插入坐标
               anchor.setRow1(3);
               anchor.setCol1(4);
               // 插入图片
               Picture pict = drawing.createPicture(anchor, pictureIdx);
               pict.resize();

               fileOut = new FileOutputStream("D:/测试Excel.xls");
               // 写入excel文件
               wb.write(fileOut);
               System.out.println("----Excle文件已生成------");
          } catch (Exception e) {
               e.printStackTrace();
          }finally{
               if(fileOut != null){
                    try {
                         fileOut.close();
                    } catch (IOException e) {
                         e.printStackTrace();
                    }
               }
          }
     }
}
