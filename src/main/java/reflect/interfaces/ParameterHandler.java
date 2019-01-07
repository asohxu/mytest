package reflect.interfaces;

/**
 * @author huang_kangjie
 * @date 2019-01-04 17:40
 * @since 1.0.3
 **/
public class ParameterHandler {

     private String paramtype(String type){
          if (type.equalsIgnoreCase("boolean")) {
               return "boolean";
          } else if (type.equalsIgnoreCase("string")) {
               return "String";
          } else if (type.equalsIgnoreCase("byte")) {
               return "byte";
          } else if (type.equalsIgnoreCase("short")) {
               return "short";
          } else if (type.equalsIgnoreCase("int") || type.equalsIgnoreCase("integer")) {
               return "Integer";
               //return "int";
          } else if (type.equalsIgnoreCase("Long") || type.equalsIgnoreCase("long")) {
               return "Long";
               //return "long";
          } else if (type.equalsIgnoreCase("float")) {
               return "float";
          } else if (type.equalsIgnoreCase("double")) {
               return "double";
          }
          return "";
     }

}
