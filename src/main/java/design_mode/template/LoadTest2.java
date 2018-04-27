package design_mode.template;

/**
 * @author huang_kangjie
 * @create 2018-04-27 11:16
 **/
public class LoadTest2 extends  ClassLoaderTemlate{
     @Override
     public void loadBefor() {
          System.out.println("加载测试2：加载前准备");
     }

     @Override
     public void loading() {
          System.out.println("加载测试2：正在加载");
     }

     @Override
     public void loadafter() {
          System.out.println("加载测试2：加载后清理缓存");
     }
}
