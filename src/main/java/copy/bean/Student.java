package copy.bean;

/**
 * @author huang_kangjie
 * @version 1.0.3
 * @date 2018-11-20 16:38
 **/
public class Student {

     private int sex;
     private int age;
     private String name;
     private String remark;

     public Student(int sex, int age, String name, String remark) {
          this.sex = sex;
          this.age = age;
          this.name = name;
          this.remark = remark;
     }

     public Student() {

     }


     public int getSex() {
          return sex;
     }

     public void setSex(int sex) {
          this.sex = sex;
     }

     public int getAge() {
          return age;
     }

     public void setAge(int age) {
          this.age = age;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getRemark() {
          return remark;
     }

     public void setRemark(String remark) {
          this.remark = remark;
     }
}
