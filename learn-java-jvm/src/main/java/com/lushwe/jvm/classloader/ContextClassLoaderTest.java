package com.lushwe.jvm.classloader;

/**
 * 说明：上下文类加载器测试
 *
 * @author Jack Liu
 * @date 2020-06-10 16:30
 * @since 0.1
 */
public class ContextClassLoaderTest {

    public static void main(String[] args) {

        ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();

        ClassLoader classLoader2 = ContextClassLoaderTest.class.getClassLoader();

        System.out.println(classLoader1);
        System.out.println(classLoader1 == classLoader2);

        // 果然，默认情况下，
        // 通过当前线程的 getContextClassLoader 方法获得的类加载器对象与通过当前类的的 getClassLoader 方法获得的类加载器对象相同
        // 均为系统类加载器：sun.misc.Launcher$AppClassLoader

    }
}
