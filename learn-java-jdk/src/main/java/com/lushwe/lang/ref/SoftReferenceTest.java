package com.lushwe.lang.ref;

import java.lang.ref.SoftReference;

/**
 * 说明：软引用测试
 *
 * @author Jack Liu
 * @date 2020-09-10 10:17
 * @since 0.1
 */
public class SoftReferenceTest {

    public static void main(String[] args) {

        testSoftReference();

    }

    /**
     * 软引用
     */
    private static void testSoftReference() {

        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(softReference.get());
        System.gc();
        System.out.println(softReference.get());

        byte[] bytes = new byte[1024 * 1024 * 10];
        System.out.println("软引用被回收了：" + softReference.get());

    }
}
