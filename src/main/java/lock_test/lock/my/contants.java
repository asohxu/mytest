package lock_test.lock.my;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：
 *
 * @author 作者 : huang_kangjie
 * @date 创建时间：2018年3月15日
 * @version v1.0.
 * 
 */
public class contants {

	/**
	 * 商品总个数
	 */
	public static volatile int num = 10;
	
	
	/**
	 * 成功获取到商品的用户
	 */
	public static List<Integer> successUser = new ArrayList<>(10);
}
