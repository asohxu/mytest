package clazz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 类说明
 *
 * @author 作者 : huang_kangjie
 * @version 创建时间：2017年3月7日
 * 
 */
public class Clazz {
	
	public  static Class<Clazz> clazz = null;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			clazz = (Class<Clazz>) Class.forName("clazz.Clazz");
			try {
				Method m = clazz.getDeclaredMethod("print", Map.class);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("test", "test");
				try {
					m.invoke(clazz, map);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void print(Map<String, Object> map){
		System.out.println("反射实现一个类" + map);
	}
}
