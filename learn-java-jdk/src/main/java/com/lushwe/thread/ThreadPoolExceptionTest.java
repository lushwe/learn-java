package com.lushwe.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 说明：线程池中的一个线程执行任务异常，该线程能否继续执行下一个任务
 *
 * @author Jack Liu
 * @date 2020-01-13 11:22
 * @since 0.1
 */
public class ThreadPoolExceptionTest {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10),
            new ThreadPoolExecutor.DiscardPolicy());


    public static void main(String[] args) {

        executor.execute(() -> System.out.println(Thread.currentThread().getName() + " " + (1 / 0)));
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.execute(() -> System.out.println(Thread.currentThread().getName() + " " + (1 / 1)));
        executor.execute(() -> System.out.println(Thread.currentThread().getName() + " " + (1 / 2)));
        executor.execute(() -> System.out.println(Thread.currentThread().getName() + " " + (1 / 3)));
        executor.execute(() -> System.out.println(Thread.currentThread().getName() + " " + (1 / 4)));

        executor.shutdown();

        // 总结
        // 线程池中的线程执行任务出现异常，该线程会结束
        // 当其他任务进来时，会重新创建一个线程执行任务
    }
}
