package field;

import excl.poi.util.ExcelVOAttribute;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author huang_kangjie
 * @create 2018-04-27 14:41
 **/
public class Test {

     public static void main(String[] args) {
          try {
               //使用反射第一步:获取操作类UserBean所对应的Class对象
               Class<?> cls = Class.forName("field.UserBean");
               //生成UserBean实例
               Object obj = cls.newInstance();
               Field[] fileds1 = cls.getFields();
               for (Field f : fileds1) {
                    System.out.println("getFields()获取所有的public属性 :" + f.getName() + " = " + f.get(obj));
               }

               Field f1 = cls.getField("num1");
               System.out.println("getField( 'string' 属性名) 获取特定属性，但是只能获取public的， getField(),属性名" + f1.getName());

               Field[] fields2 = cls.getDeclaredFields();
               Arrays.stream(fields2).forEach(
                       field -> {
                            try {
                                 //通过这个设置，可以将private的属性，设置为可操作的属性
                                 field.setAccessible(true);
                                 System.out.println("getDeclaredFields() 获取所有的属性 "
                                         + Modifier.toString(field.getModifiers()) + " "
                                         + field.getGenericType() + " "
                                         + field.getName() + " = "
                                         + field.get(obj));
                            } catch (IllegalAccessException e) {
                                 e.printStackTrace();
                            }
                       });
               Field f2 = cls.getDeclaredField("num4");
               System.out.println("getDeclaredField(\"num4\") 获取特定的属性，可以获取private的属性 :" + f2.getName() + " = " );

               //获取某个特定的方法
               Method m = cls.getDeclaredMethod("print");
               m.invoke(cls.newInstance());

               //模拟封装参数
               Field[] fields3 = cls.getDeclaredFields();
               Arrays.stream(fields3).forEach(
                       field -> {
                            try {
                                 field.setAccessible(true);
                                 String type = field.getGenericType().toString();
                                 Object s = field.getType();
                                 if(type.equals("class java.lang.String")){
                                      field.set(obj, "String 值");
                                 }
                                 if(type.equals("int") || type.equals("class java.lang.Integer")){
                                      field.set(obj, 123);
                                 }
                                 //测试获取注解
                                 ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
                                 if(attr != null) {
                                      System.out.println(">>>>>>>>>>>>>> " + field.getName() + "有注解,获取所有注解的值");
                                      System.out.println("name = " + attr.name());
                                      System.out.println("column = " + attr.column());
                                      System.out.println("prompt = " + attr.prompt());
                                      System.out.println("combo = " + attr.combo()[0] + ", " + attr.combo()[1]);
                                      System.out.println("isExport = " + attr.isExport());
                                      System.out.println("width = " + attr.width());
                                 }
                            } catch (Exception e) {
                                 e.printStackTrace();
                            }
                       });

               UserBean user = (UserBean) obj;
               System.out.println(" >>>>>>> 通过自己set以后： num4 = " + user.getNum4());
               System.out.println(" >>>>>>> 通过自己set以后： num5 = " + user.getNum5());
               System.out.println(" >>>>>>> 通过自己set以后： num6 = " + user.getNum6());




          } catch (Exception e) {
               e.printStackTrace();
          }
     }
}
