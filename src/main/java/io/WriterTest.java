package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author huang_kangjie
 * @create 2018-08-08 18:00
 **/
public class WriterTest {

     public static void main(String[] args) {
          writer("this is test ");
          writer("this is test ");
          writer("this is test ");
     }

     /**
      * 写文件
      * @param content
      */
     public static void writer(String content) {
          FileWriter fw = null;
          try {
               //如果文件存在，则追加内容；如果文件不存在，则创建文件
               File f = new File("D:\\dd.txt");
               fw = new FileWriter(f, true);
          } catch (IOException e) {
               e.printStackTrace();
          }
          PrintWriter pw = new PrintWriter(fw);
          pw.println(content);
          pw.flush();
          try {
               fw.flush();
               pw.close();
               fw.close();
          } catch (IOException e) {
               e.printStackTrace();
          }
     }
}
