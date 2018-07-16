package io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 用nio拷贝文件
 * @author huang_kangjie
 * @create 2018-07-12 15:35
 **/
public class NioCopyFile {

     public static void main(String[] args) {
          FileInputStream fin = null;
          FileOutputStream fout = null;
          try {
               //构建原始文件和拷贝后的文件
               String infile = "D://test/test.xls";
               String outfile = "D://test/test-copy.xls";

               fin = new FileInputStream(infile);
               fout = new FileOutputStream(outfile);

               //获取读的通道
               FileChannel fcin = fin.getChannel();

               ////获取写的管道
               FileChannel fcout = fout.getChannel();

               ByteBuffer buffer = ByteBuffer.allocate(1024);

               while (true) {
                    //先清空一次缓冲区
                    buffer.clear();
                    int data = fcin.read(buffer);
                    if(data == -1) {
                         break;
                    }
                    //将指针指向头部
                    buffer.flip();
                    fcout.write(buffer);
               }
               System.out.println("文件拷贝成功");
          } catch (Exception e) {
               e.printStackTrace();
          } finally {
               try {
                    fin.close();
                    fout.close();
               } catch (IOException e) {
                    e.printStackTrace();
               }

          }


     }

}
