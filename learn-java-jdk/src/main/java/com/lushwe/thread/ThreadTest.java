package com.lushwe.thread;

import java.util.Map;

/**
 * 说明：Thread测试
 *
 * @author Shiwei Liu
 * @date 2019-06-12 21:27
 * @since 1.0
 */
public class ThreadTest {

    Map<String, String> map = null;

    public static void main(String[] args) throws InterruptedException {


        Thread thread1 = new Thread(() -> {

            System.out.println("线程1执行开始");
            System.out.println("线程1睡眠1秒");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1执行完毕");

        });

        Thread thread2 = new Thread(() -> {

            try {
                System.out.println("线程2开始join线程1");
                thread1.join();
                System.out.println("线程2完成join线程1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程2执行开始");
            System.out.println("线程2睡眠1秒");
            System.out.println("线程2执行完毕");

        });


        Thread thread3 = new Thread(() -> {

            synchronized (thread1) {

                System.out.println("线程3拿到thread1对象的锁");

                while (thread1.isAlive()) {
                    try {
                        thread1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("线程3释放thread1对象的锁");
            }

        });

        thread1.start();
        thread2.start();
        thread3.start();


//        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
//        System.out.println(JSON.toJSONString(map, true));
    }
}
