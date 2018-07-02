/**
 * ThreadLocal用于保存某个线程共享变量：对于同一个static ThreadLocal，不同线程只能从中get，set，remove自己的变量，而不会影响其他线程的变量。
 1、ThreadLocal.get: 获取ThreadLocal中当前线程共享变量的值。
 2、ThreadLocal.set: 设置ThreadLocal中当前线程共享变量的值。
 3、ThreadLocal.remove: 移除ThreadLocal中当前线程共享变量的值。
 4、ThreadLocal.initialValue: ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值。
 */
package localthread;