package design_mode.template;

/**
 * @author huang_kangjie
 * @create 2018-04-27 11:11
 **/
public abstract class ClassLoaderTemlate {

     public void load() {
          System.out.println("--------------加载前准备----------------");
          this.loadBefor();
          System.out.println("--------------开始加载...----------------");
          this.loading();
          this.loadafter();
          System.out.println("--------------加载完毕----------------");
     }

     public abstract void loadBefor();

     public abstract void loading();

     public abstract void loadafter();
}
