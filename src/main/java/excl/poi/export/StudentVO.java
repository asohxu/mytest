package excl.poi.export;

/**
 * 学生实体，demo
 * @author huang_kangjie
 * @create 2018-04-27 16:28
 */
public class StudentVO {
    @ExcelVOAttribute(name = "序号", column = "A")
    public int id;

    @ExcelVOAttribute(name = "姓名", column = "B", isExport = true)
    public String name;

    @ExcelVOAttribute(name = "年龄", column = "C", prompt = "年龄保密哦!", isExport = true)
    public int age;

    @ExcelVOAttribute(name = "班级", column = "D", combo = {"五期提高班", "六期提高班",
            "七期提高班"})
    public String clazz;

    @ExcelVOAttribute(name = "公司", column = "E", combo = {"五期提高班", "六期提高班",
            "七期提高班", "天融信"})
    public String company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "StudentVO [id=" + id + ", name=" + name + ", company="
                + company + ", age=" + age + ", clazz=" + clazz + "]";
    }
}
