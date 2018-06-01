package test.thread.callable_test.my.callable;

import java.util.Random;
import java.util.concurrent.Callable;

import test.thread.callable_test.my.bean.ResultBean;

/**
 * @描述：
 *
 * @author 作者 : huang_kangjie
 * @date 创建时间：2018年3月15日
 * @version v1.0.
 * 
 */
public class CallableTaskThread implements Callable<ResultBean>{
	
	private int no;
	
	public CallableTaskThread (int no){
		this.no = no;
	}

	@Override
	public ResultBean call() throws Exception {
		
		System.out.println(Thread.currentThread().getName() + ",开始执行call()");
		
		ResultBean rs = new ResultBean();
		rs.setCode((new Random().nextInt(10)) + "");
		rs.setMsg("成功");
		
		Thread.sleep(2000);
		
		if(no == 2){
			Thread.sleep(5000);
		}
		
		System.out.println(Thread.currentThread().getName() + "执行完毕call()，返回执行结果");
		
		return rs;
		
	}

}
