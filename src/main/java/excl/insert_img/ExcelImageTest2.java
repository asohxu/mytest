package excl.insert_img;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * 指定单元格的起始和终止
 * @author huang_kangjie
 * @create 2018-05-18 13:57
 **/
public class ExcelImageTest2 {
     public static void main(String[] args) {
          FileOutputStream fileOut = null;
          BufferedImage bufferImg = null;
          //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
          try {
               ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
               bufferImg = ImageIO.read(new File("D:/8.jpg"));
               ImageIO.write(bufferImg, "jpg", byteArrayOut);

               HSSFWorkbook wb = new HSSFWorkbook();
               HSSFSheet sheet1 = wb.createSheet("test picture");
               //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
               HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
               HSSFRow row = sheet1.createRow(2);
               //anchor主要用于设置图片的属性
               //关于HSSFClientAnchor(dx1,dy1,dx2,dy2,col1,row1,col2,row2)的参数，有必要在这里说明一下：
               //dx1：起始单元格的x偏移量，如例子中的255表示直线起始位置距A1单元格左侧的距离；
               //dy1：起始单元格的y偏移量，如例子中的125表示直线起始位置距A1单元格上侧的距离；
               //dx2：终止单元格的x偏移量，如例子中的1023表示直线起始位置距C3单元格左侧的距离；
               //dy2：终止单元格的y偏移量，如例子中的150表示直线起始位置距C3单元格上侧的距离；
               //col1：起始单元格列序号，从0开始计算；
               //row1：起始单元格行序号，从0开始计算，如例子中col1=0,row1=0就表示起始单元格为A1；
               //col2：终止单元格列序号，从0开始计算；
               //row2：终止单元格行序号，从0开始计算，如例子中col2=2,row2=2就表示起始单元格为C3；
               HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 255,(short) 2, 2, (short) 3, 2);
               //anchor.setAnchorType(3);
               row.setHeight((short) 2000);
               //插入图片
               patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
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
