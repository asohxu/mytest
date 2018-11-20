package copy;

import copy.bean.Student;
import copy.bean.Teacher;

/**
 * @author huang_kangjie
 * @version 1.0.3
 * @date 2018-11-20 16:40
 **/
public class Test {

     public static void main(String[] args) throws Exception {
          Student s1 = new Student(1, 2, "张三", "党员");
          Student s2 = new Student();
          BeanCopierUtil.copy(s1, s2);
          System.out.println(s2.getAge()  + " ; " + s2.getSex() + " ; " + s2.getName() + " ; " + s2.getRemark() );

          System.out.println("-----------------------------------------------------");

          Teacher t = new Teacher();
          BeanCopierUtil.copy(s1, t);
          System.out.println(s2.getName() + " 的老师名字： " + t.getName() + " ; 性别是" + t.getSex() + " ; 工作室：" + t.getJob()
                  + " ; 英文名：" + t.getEngName());

     }
}
