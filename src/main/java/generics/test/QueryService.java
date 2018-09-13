package generics.test;

/**
 * @author huang_kangjie
 * @create 2018-09-13 16:35
 **/
public interface QueryService<P, R> {

     /**
      * 泛型查询
      * @param request
      * @return
      */
     R query(P request);

}
