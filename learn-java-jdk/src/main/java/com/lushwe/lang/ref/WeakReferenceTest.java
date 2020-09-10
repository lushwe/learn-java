package com.lushwe.lang.ref;

import java.lang.ref.WeakReference;

/**
 * 说明：弱引用测试
 *
 * @author Jack Liu
 * @date 2020-09-10 10:18
 * @since 0.1
 */
public class WeakReferenceTest {

    public static void main(String[] args) {

        testWeakReference();

    }

    /**
     * 弱引用
     */
    private static void testWeakReference() {

        WeakReference<byte[]> weakReference = new WeakReference<>(new byte[1]);

        // 获取弱引用实例对象
        System.out.println(weakReference.get());
        System.gc();
        System.out.println("弱引用被回收了：" + weakReference.get());
    }
}
