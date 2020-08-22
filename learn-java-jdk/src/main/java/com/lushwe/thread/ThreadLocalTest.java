package com.lushwe.thread;

/**
 * 说明：ThreadLocal测试
 *
 * @author Jack Liu
 * @date 2019-06-19 16:10
 * @since 1.0
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        ThreadTask threadTask1 = new ThreadTask("ThreadTask1");
        ThreadTask threadTask2 = new ThreadTask("ThreadTask2");
        ThreadTask threadTask3 = new ThreadTask("ThreadTask3");

        threadTask1.start();
        threadTask2.start();
        threadTask3.start();


        // 总结
        // ThreadLocal类型的变量，在主线程设置的值，在子线程中是拿不到的
        // 如果当前ThreadLocal对象没有强引用，而且当前线程一直存活，那么垃圾回收时，会回收ThreadLocal对象

        // Thread -> ThreadLocalMap -> Entry -> Key -> ThreadLocal

        // 为什么Entry中的Key用弱引用呢？
        // 我们先假设下，Entry中的Key用强引用，ThreadLocal对象没有其他强引用引用，但是从上面的引用链可知，ThreadLocal一直被Entry中Key强引用，生命周期和线程一样
        // 这样的话，ThreadLocal对象也会造成内存泄漏

        // 而使用弱引用，Key会被回收，只有剩下的Value会造成内存泄漏，不过下一次ThreadLocalMap调用set,get,remove的时候会清除key=null的value

        // 所以造成内存泄漏的根本原因是ThreadLocalMap的生命周期和Thread一样，如果使用完后不及时清理，会造成内存泄漏
        // 弱引用在一定程度上缓解内存泄漏，但是不能根本上解决内存泄漏

        // 所以，在使用ThreadLocal进行线程隔离时，一定要在使用完后remove()
    }

    static class ThreadTask extends Thread {

        private String name;

        public ThreadTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ", threadLocal=" + threadLocal);
            System.out.println(Thread.currentThread().getName() + ", 1 threadLocal.get()=" + threadLocal.get());
            threadLocal.set(name);
            System.out.println(Thread.currentThread().getName() + ", 2 threadLocal.get()=" + threadLocal.get());
            threadLocal.remove();
            System.out.println(Thread.currentThread().getName() + ", 3 threadLocal.get()=" + threadLocal.get());
        }
    }

}
