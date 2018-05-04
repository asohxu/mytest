package callback;

/**
 * 小王：问问题的人
 * @author huang_kangjie
 * @create 2018-05-03 17:38
 **/
public class Wang implements ICallback{

     public void ask(String qustion) {
          System.out.println("我是小王："+qustion+"，这道题有点难，问问小李吧。。。");
          Thread t = new Thread(new Runnable() {
               @Override
               public void run() {
                    Li li = new Li(new Wang());
                    li.answer(qustion);
               }
          });
          t.start();
          System.out.println("我是小王：我先出玩一下，你等下给我答案就是了");

     }

     @Override
     public void solve(String result) {
          System.out.println("解决答案是： " + result);
     }
}
