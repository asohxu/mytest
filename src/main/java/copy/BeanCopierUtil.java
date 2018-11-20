package copy;

import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 使用的cglb动态代理实现
 *
 * @author huang_kangjie
 * @version 1.0.3
 * @date 2018-11-20 16:33
 **/
public class BeanCopierUtil {


     /**
      *  复制对象属性
      *
      *  两个对象的属性名字、返回值都必须一样才能实现拷贝
      *
      * @param source    源对象
      * @param target    目标对象
      * @throws Exception
      */
     public static void copy(Object source, Object target) throws Exception {
          if (!ObjectUtils.allNotNull(source, target)) {
               throw new Exception("源对象和目标对象都不能为空");
          }
          BeanCopier beanCopier = BeanCopier.create(source.getClass(),
                  target.getClass(), false);
          beanCopier.copy(source, target, null);
     }
}
