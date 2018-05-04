package callback;

/**
 * 这是一个回调接口
 * @author huang_kangjie
 * @create 2018-05-03 17:35
 **/
public interface ICallback {

     /**
      * 这个是小李知道答案时要调用的函数告诉小王，也就是回调函数
      * @param result 是答案
      */
     public void solve(String result);

}
