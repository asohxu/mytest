package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author huang_kangjie
 * @create 2018-04-28 14:13
 **/
public class ReadLineTest {
     public static void main(String[] args) throws IOException
     {

          //BufferedReader是可以按行读取文件
          FileInputStream inputStream = new FileInputStream("d://Test.txt");
          BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

          String str = null;
          while((str = bufferedReader.readLine()) != null)
          {
               List<String> list = new ArrayList<>();
               String[] arr = str.split(" ");
               Arrays.stream(arr).forEach( s -> list.add(s));
               int len = list.size();
               if(len != 4) {
                    System.out.println(">>>>>> " + list);
               }
               System.out.println("INSERT INTO `ows`.`t_option` (`code`, `op_key`, `op_value`) VALUES ('1012', '"+list.get(2)+"', '"+list.get(0)+"');");

          }

          //close
          inputStream.close();
          bufferedReader.close();

     }
}
