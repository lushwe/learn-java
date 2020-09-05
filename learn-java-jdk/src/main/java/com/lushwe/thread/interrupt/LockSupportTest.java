package com.lushwe.thread.interrupt;

import java.util.concurrent.locks.LockSupport;

/**
 * 说明：线程挂起，唤醒
 *
 * @author Jack Liu
 * @date 2020-05-13 10:56
 * @since 0.1
 */
public class LockSupportTest {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            System.out.println("挂起线程 " + Thread.currentThread().getName());
            LockSupport.park(Thread.currentThread());
            System.out.println("线程 " + Thread.currentThread().getName() + " 被唤醒, 线程中断状态 " + Thread.currentThread().isInterrupted());
            System.out.println("线程 " + Thread.currentThread().getName() + " 被唤醒, 线程中断状态 " + Thread.interrupted());
            System.out.println("线程 " + Thread.currentThread().getName() + " 被唤醒, 线程中断状态 " + Thread.currentThread().isInterrupted());
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("唤醒线程 " + thread1.getName());
            LockSupport.unpark(thread1);
        });

        Thread thread3 = new Thread(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("中断线程 " + thread1.getName());
            thread1.interrupt();
        });

        thread1.start();
        thread2.start();
        thread3.start();

        // 总结：
        // 线程被挂起后，有两种方法被唤醒
        // 方法一：其他线程调用 LockSupport.unpark 方法，取消挂起
        // 方法二：其他线程调用 interrupt() 方法，中断线程
    }
}
