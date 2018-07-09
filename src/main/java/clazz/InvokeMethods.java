package clazz;

/**
 * 类说明
 *
 * @author 作者 : huang_kangjie
 * @version 创建时间：2017年3月7日
 * 
 */
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class InvokeMethods {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		
		
		Class cl = null;
		try {
			cl = Class.forName("clazz.Employee");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Employee emp = null;
		try {
			emp = (Employee) cl.newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		Class cl = emp.getClass();
		// 是Class，而不是class
		// /getClass获得emp对象所属的类型的对象，Class就是类的类
		// /Class是专门用来描述类的类，比如描述某个类有那些字段，
		// /方法，构造器等等！
		try {
			// /getMethod方法第一个参数指定一个需要调用的方法名称
			// /这里是Employee类的setAge方法，第二个参数是需要调用
			// 方法的参数类型列表，是参数类型！如无参数可以指定null
			// /该方法返回一个方法对象
			Method sAge = cl.getMethod("setAge", new Class[] { int.class });// 参数必须和方法中一样int和Integer，double和Double被视为不同的类型
			Method gAge = cl.getMethod("getAge", null);
			Method pName = cl.getMethod("printName", new Class[] { String.class });
			
			Method print = cl.getMethod("print", Map.class);

			Object[] args1 = { new Integer(25) };
			// 参数列表
			// emp为隐式参数该方法不是静态方法必须指定
			sAge.invoke(emp, args1);
			Integer AGE = (Integer) gAge.invoke(emp, null);
			int age = AGE.intValue();
			System.out.println("The Employee Age is: " + age);
			Object[] args3 = { new String("Jack") };
			pName.invoke(emp, args3);
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("key", "value");
			print.invoke(emp, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}

class Employee {
	// 定义一个员工类
	public Employee() {
		age = 0;
		name = null;
	}

	// 将要被调用的方法
	public void setAge(int a) {
		age = a;
	}

	// 将要被调用的方法
	public int getAge() {
		return age;
	}

	// 将要被调用的方法
	public void printName(String n) {
		name = n;
		System.out.println("The Employee Name is: " + name);
	}
	
	public void print(Map<String, Object> map){
		System.out.println("测试打印方法  ： " + map);
	}

	private int age;
	private String name;
}