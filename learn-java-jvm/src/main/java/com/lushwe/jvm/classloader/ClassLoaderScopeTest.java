package com.lushwe.jvm.classloader;

/**
 * 说明：类加载器加载范围测试
 *
 * @author Jack Liu
 * @date 2020-09-18 22:50
 * @since 0.1
 */
public class ClassLoaderScopeTest {

    public static void main(String[] args) {

        String pathBoot = System.getProperty("sun.boot.class.path");
        System.out.println(pathBoot.replaceAll(":", System.lineSeparator()));

        System.out.println("=============================");
        String pathExt = System.getProperty("java.ext.dirs");
        System.out.println(pathExt.replaceAll(":", System.lineSeparator()));

        System.out.println("=============================");
        String pathApp = System.getProperty("java.class.path");
        System.out.println(pathApp.replaceAll(":", System.lineSeparator()));
    }
}
