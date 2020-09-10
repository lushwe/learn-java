package com.lushwe.lang;

/**
 * 说明：自定义类加载器测试
 *
 * @author Jack Liu
 * @date 2020-03-10 15:20
 * @since 0.1
 */
public class MyClassLoaderTest {

    public static void main(String[] args) throws Exception {

        MyClassLoader myClassLoader = new MyClassLoader();

        Object obj1 = myClassLoader.loadClass("com.lushwe.lang.MyClassLoaderTest").newInstance();

        System.out.println(obj1.getClass());
        System.out.println(obj1 instanceof MyClassLoaderTest);

    }
}
