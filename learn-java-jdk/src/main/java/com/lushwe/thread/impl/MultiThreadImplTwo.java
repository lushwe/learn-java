package com.lushwe.thread.impl;

/**
 * 方式二：实现Runnable接口
 *
 * @author Jack Liu
 * @date 2020-08-08 14:30
 * @since 0.1
 */
public class MultiThreadImplTwo {

    public static void main(String[] args) {

        MyTaskTwo task1 = new MyTaskTwo();
        MyTaskTwo task2 = new MyTaskTwo();

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }
}

class MyTaskTwo implements Runnable {

    @Override
    public void run() {

        System.out.println("线程 " + Thread.currentThread().getName() + " 执行run方法");

    }
}

