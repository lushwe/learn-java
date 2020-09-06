package com.lushwe.thread.impl;

/**
 * 方式一：继承Thread类，重写run方法
 *
 * @author Jack Liu
 * @date 2020-08-08 14:30
 * @since 0.1
 */
public class MultiThreadImplOne {

    public static void main(String[] args) {

        MyTaskOne task1 = new MyTaskOne();
        MyTaskOne task2 = new MyTaskOne();

        task1.start();
        task2.start();

    }
}

class MyTaskOne extends Thread {

    @Override
    public void run() {

        System.out.println("线程 " + Thread.currentThread().getName() + " 执行run方法");

    }
}
