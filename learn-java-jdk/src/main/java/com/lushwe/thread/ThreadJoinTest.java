package com.lushwe.thread;

/**
 * 说明：Join方法使用学习
 *
 * @author Jack Liu
 * @date 2020-03-30 16:01
 * @since 0.1
 */
public class ThreadJoinTest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {

            System.out.println("子线程将执行耗时三秒的逻辑");
            // 模拟执行耗时三秒
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程执行完毕");
        });

        thread.start();

        thread.join();

        System.out.println("主线程执行");

    }
}
