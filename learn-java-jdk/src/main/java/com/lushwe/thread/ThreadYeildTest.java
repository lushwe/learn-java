package com.lushwe.thread;

/**
 * 说明：测试线程 yield 方法
 *
 * @author Jack Liu
 * @date 2020-03-30 16:01
 * @see Thread#yield()
 * @since 0.1
 */
public class ThreadYeildTest {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {

            for (int i = 1; i <= 100; i++) {
                System.out.println("线程 " + Thread.currentThread().getName() + " 执行, i=" + i);
                if (i % 10 == 0) {
                    // 让出机会
                    Thread.yield();
                }
            }

        });

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println("线程 " + Thread.currentThread().getName() + " 执行, i=" + i);
                if (i % 10 == 0) {
                    // 让出机会
                    Thread.yield();
                }
            }
        });

        t1.start();
        t2.start();

        // 总结
        // 执行 Thread.yield 会让当前线程让出CPU资源，进入到就绪状态，有可能立马又会获得CPU资源继续执行
        // Thread.yield 并不能保证其他线程一定能得到执行
        // Thread.yield 是一个静态方法，表示执行这个方法的线程会让出资源

    }
}
