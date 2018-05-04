package callback;

/**
 * 小李：回答问题的人
 * @author huang_kangjie
 * @create 2018-05-03 17:35
 **/
public class Li {
     private ICallback callback;

     public Li(ICallback callback){
          this.callback = callback;
     }


     public void answer(String question){
          System.out.println("我是小李：收到小明的问题：" + question);
          System.out.println("我是小李：这个问题有点难，容我思考3秒");
          try {
               for (int i = 1; i < 4; i++) {
                    System.out.println("思考： " + i);
                    Thread.sleep(1000);
               }
          } catch (InterruptedException e) {
               e.printStackTrace();
          }
          callback.solve("2");
     }
}
