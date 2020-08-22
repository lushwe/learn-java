package com.lushwe.thread;

/**
 * 说明：InheritableThreadLocal使用示例
 *
 * @author Jack Liu
 * @date 2020-01-14 10:37
 * @since 0.1
 */
public class InheritableThreadLocalTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal();
    private static ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal();

    public static void main(String[] args) {

        threadLocal.set("主线程设置A");
        inheritableThreadLocal.set("主线程设置B");

        System.out.println("主线程获取的结果11：" + threadLocal.get());
        System.out.println("主线程获取的结果12：" + inheritableThreadLocal.get());

        new Thread(() -> {

            System.out.println("子线程获取的结果1：" + threadLocal.get());
            System.out.println("子线程获取的结果2：" + inheritableThreadLocal.get());

        }).start();

        System.out.println("主线程获取的结果21：" + threadLocal.get());
        System.out.println("主线程获取的结果22：" + inheritableThreadLocal.get());

        // 总结
        // ThreadLocal类型的变量，在主线程设置的值，在子线程中拿不到值
        // InheritableThreadLocal类型的变量，在主线程设置的值，在子线程中也能拿到值

        // 原理在 Thread.init(ThreadGroup, Runnable, String, long, AccessControlContext, boolean) 初始化方法中有一段代码
        //       if (inheritThreadLocals && parent.inheritableThreadLocals != null)
        //            this.inheritableThreadLocals =
        //                ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);

        // 所以，如果定义 InheritableThreadLocal 类型的对象，在创建子线程的时候，会负责父线程 InheritableThreadLocal 类型的对象信息
    }
}
