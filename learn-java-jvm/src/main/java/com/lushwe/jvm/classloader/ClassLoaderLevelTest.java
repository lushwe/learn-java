package com.lushwe.jvm.classloader;

/**
 * 说明：类加载器级别测试
 *
 * @author Jack Liu
 * @date 2020-09-18 22:13
 * @since 0.1
 */
public class ClassLoaderLevelTest {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(sun.awt.HKSCS.class.getClassLoader());
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
        System.out.println(ClassLoaderLevelTest.class.getClassLoader());

        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());
        System.out.println(ClassLoaderLevelTest.class.getClassLoader().getClass().getClassLoader());
    }
}
