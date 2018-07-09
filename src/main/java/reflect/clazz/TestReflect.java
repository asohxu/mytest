package reflect.clazz;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 类说明
 *
 * @author 作者 : huang_kangjie
 * @version 创建时间：2017年3月7日
 * 
 */
public class TestReflect implements Serializable{

	private static final long serialVersionUID = 4013346925954263388L;

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TestReflect testReflect = new TestReflect();
		
		//通过一个对象获得完整的包名和类名
        System.out.println(testReflect.getClass().getName());
        
        //实例化Class类对象
        Class<?> class1 = null;
        Class<?> class2 = null;
        Class<?> class3 = null;
        // 一般采用这种形式
        try {
			class1 = Class.forName("reflect.clazz.TestReflect");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        class2 = new TestReflect().getClass();
        class3 = TestReflect.class;
        System.out.println("类名称   " + class1.getName());
        System.out.println("类名称   " + class2.getName());
        System.out.println("类名称   " + class3.getName());
        
        //获取一个对象的父类与实现的接口
        Class<?> clazz = Class.forName("reflect.clazz.TestReflect");
        // 取得父类
        Class<?> parentClass = clazz.getSuperclass();
        System.out.println("clazz的父类为：" + parentClass.getName());
        // clazz的父类为： java.lang.Object
        // 获取所有的接口
        Class<?> intes[] = clazz.getInterfaces();
        System.out.println("clazz实现的接口有：");
        for (int i = 0; i < intes.length; i++) {
            System.out.println((i + 1) + "：" + intes[i].getName());
        }
        // clazz实现的接口有：
        // 1：java.io.Serializable
        
        Class<?> class4 = null;
        class4 = Class.forName("clazz.User");
        // 第一种方法，实例化默认构造方法，调用set赋值
        User user = (User) class4.newInstance();
        user.setAge(20);
        user.setName("Rollen");
        System.out.println(user);
        // 结果 User [age=20, name=Rollen]
        // 第二种方法 取得全部的构造函数 使用构造函数赋值
        Constructor<?> cons[] = class4.getConstructors();
        // 查看每个构造方法需要的参数
        for (int i = 0; i < cons.length; i++) {
            Class<?> clazzs[] = cons[i].getParameterTypes();
            System.out.print("cons[" + i + "] (");
            for (int j = 0; j < clazzs.length; j++) {
                if (j == clazzs.length - 1)
                    System.out.print(clazzs[j].getName());
                else
                    System.out.print(clazzs[j].getName() + ",");
            }
            System.out.println(")");
        }
        // 结果
        // cons[0] (java.lang.String)
        // cons[1] (int,java.lang.String)
        // cons[2] ()
        user = (User) cons[0].newInstance(10,"Rollen");
        System.out.println(user);
        // 结果 User [age=0, name=Rollen]
        user = (User) cons[1].newInstance("Rollen");
        System.out.println(user);
        // 结果 User [age=20, name=Rollen]
	}
}

class User {
    private int age;
    private String name;
    public User() {
        super();
    }
    public User(String name) {
        super();
        this.name = name;
    }
    public User(int age, String name) {
        super();
        this.age = age;
        this.name = name;
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
    @Override
    public String toString() {
        return "User [age=" + age + ", name=" + name + "]";
    }
}
