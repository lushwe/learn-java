package com.lushwe.thread.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 方式三：实现Callable接口
 *
 * @author Jack Liu
 * @date 2020-08-08 14:30
 * @since 0.1
 */
public class MultiThreadImplThree {

    public static void main(String[] args) {


        FutureTask task1 = new FutureTask(new MyTaskThree());
        FutureTask task2 = new FutureTask(new MyTaskThree());

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

    }
}

class MyTaskThree implements Callable {

    @Override
    public Boolean call() throws Exception {
        System.out.println("线程 " + Thread.currentThread().getName() + " 执行call方法");
        return true;
    }
}

