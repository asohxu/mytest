/**
 * maven 依赖：
 *
 <dependency>
      <groupId>cglib</groupId>
      <artifactId>cglib</artifactId>
      <version>3.2.4</version>
 </dependency>

 使用cglib完成动态代理，大概的原理是：
 cglib继承被代理的类，重写方法，织入通知，动态生成字节码并运行，
 因为是继承所以final类是没有办法动态代理的
 */
package design_mode.proxy.cglib;