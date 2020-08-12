package com.lushwe;

/**
 * 说明：synchronized用法示例
 *
 * @author Jack Liu
 * @date 2020-01-14 10:51
 * @since 0.1
 */
public class SynchronizedTest {


    public static void main(String[] args) {

    }


    /**
     * 同步方法，作用范围是当前类的所有对象实例
     */
    public synchronized static void test0() {
        System.out.println("0");
    }

    /**
     * 同步方法，作用范围是当前类的对象实例
     */
    public synchronized void test1() {
        System.out.println("1");
    }


    /**
     * 同步代码块
     */
    public void test2() {

        // 作用范围是this实例
        synchronized (this) {
            System.out.println("2");
        }

        Object obj = new Object();
        // 作用范围是obj实例
        synchronized (obj) {
            System.out.println("2");
        }
    }


    /**
     * 同步代码块
     */
    public void test3() {

        // 作用范围是SynchronizedTest类，即SynchronizedTest类的所有对象实例共享该同一把锁
        synchronized (SynchronizedTest.class) {
            System.out.println("2");
        }
    }
}
