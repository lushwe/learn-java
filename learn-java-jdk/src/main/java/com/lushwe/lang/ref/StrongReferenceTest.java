package com.lushwe.lang.ref;

/**
 * 说明：强引用测试
 *
 * @author Jack Liu
 * @date 2020-09-10 10:16
 * @since 0.1
 */
public class StrongReferenceTest {

    public static void main(String[] args) {

        testStrongReference();

    }

    /**
     * 强引用
     */
    private static void testStrongReference() {
        Student student = new Student();
        student = null;
        System.gc();
    }
}
