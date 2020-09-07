package com.lushwe.cpu;

import java.util.concurrent.CountDownLatch;

/**
 * 说明：缓存行填充测试
 *
 * @author Jack Liu
 * @date 2020-09-06 18:12
 * @since 0.1
 */
public class CacheLineTest {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        CountDownLatch latch = new CountDownLatch(2);

        final MemoryObject obj = new MemoryObject();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                obj.x1++;
            }
            latch.countDown();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                obj.x2++;
            }
            latch.countDown();
        });

        t1.start();
        t2.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("耗时：" + (end - start) + "毫秒");

        // 总结：
        // 两个线程分别对x1, x2累加
        // 对象MemoryObject没有定义p1-p7变量时，累加需要4000ms左右
        // 对象MemoryObject定义p1-p7变量后，累加只需要600ms左右
        // 时间差了6倍左右

        // 原因：
        // CPU分为L1,L2,L3三级缓存
        // 数据在缓存中不是以独立的项来存储的，也不是一个单独的变量，也不是一个单独的指针
        // 缓存是由缓存行组成的，通常是64字节，并且它有效地引用主内存中一块地址
        // 一个long类型8个字节，8个long类型正好64字节，
        // 所以x1, x2中间填充7个long类型字段，可以让x1, x2在任何时候都在不同的缓存行中
        // 如果中间没有填充7个long类型字段，x1, x2很大可能在同一个缓存行，
        // 修改x1, 会造成x2对应的缓存行失效，同时修改x2, 会造成x1对应的缓存行失效
        // 不断缓存行失效，CPU需要使用x1, 或x2时，都需要重新去主内存加载，自然比直接从缓存中读取要慢很多

        // 一个开源的高新能队列框架 discuptor 就使用到了缓存行填充
    }

    static class MemoryObject {

        private volatile long x1;

        public long p1, p2, p3, p4, p5, p6, p7; // 缓存行填充

        private volatile long x2;
    }

}


