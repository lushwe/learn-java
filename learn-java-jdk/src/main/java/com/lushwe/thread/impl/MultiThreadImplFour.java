package com.lushwe.thread.impl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 方式四：线程池
 *
 * @author Jack Liu
 * @date 2020-08-08 14:42
 * @since 0.1
 */
public class MultiThreadImplFour {

    private static ExecutorService executorService = new ThreadPoolExecutor(3, 5,
            60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(32));

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                System.out.println("线程 " + Thread.currentThread().getName() + " 执行run方法");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }
}
