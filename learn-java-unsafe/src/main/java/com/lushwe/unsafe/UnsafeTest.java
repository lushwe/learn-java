package com.lushwe.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 说明：Unsafe使用测试（危险，慎用）
 *
 * @author Jack Liu
 * @date 2020-06-10 09:46
 * @since 0.1
 */
public class UnsafeTest {

    private static final long stateOffset;

    private static Unsafe unsafe = null;

    static {
        // 获取 unsafe 实例
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 初始化 stateOffset
        try {
            stateOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    private final boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    private volatile int state;

    public int getState() {
        return state;
    }

    public static void main(String[] args) {
//        AtomicInteger atomicInteger = new AtomicInteger();
//        atomicInteger.compareAndSet(0, 1);

        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(0, 0);

        Integer reference = atomicStampedReference.getReference();
        int stamp = atomicStampedReference.getStamp();

        System.out.println("reference-->" + atomicStampedReference.getReference());
        System.out.println("stamp-->" + atomicStampedReference.getStamp());
        System.out.println(atomicStampedReference.compareAndSet(reference, 1, stamp, 1));
        System.out.println(atomicStampedReference.compareAndSet(1, 0, 1, 2));
        System.out.println("reference-->" + atomicStampedReference.getReference());
        System.out.println("stamp-->" + atomicStampedReference.getStamp());
        System.out.println(atomicStampedReference.compareAndSet(reference, 1, stamp, 1));


//        UnsafeTest unsafeTest = new UnsafeTest();
//        System.out.println("state-->" + unsafeTest.getState());
//        System.out.println(unsafeTest.compareAndSetState(0, 1));
//        System.out.println("state-->" + unsafeTest.getState());

    }

}
