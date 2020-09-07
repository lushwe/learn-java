package com.lushwe.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 说明：线程通信测试
 *
 * @author Jack Liu
 * @date 2020-09-07 13:19
 * @since 0.1
 */
public class ThreadSignalTest {

    public static void main(String[] args) {

        // 顺序打印
        // 多线程之间按顺序调用, 实现 A->B->C 三个线程启动, 要求如下
        // AA打印5次, BB打印10次, CC打印15次
        // 紧接着
        // AA打印5次, BB打印10次, CC打印15次
        // 来10轮

//        testOne();
//        testTwo();

    }

    /**
     * 使用 synchronized
     */
    private static void testTwo() {

        Object obj = new Object();

        CountDownLatch latch = new CountDownLatch(1);

        final AtomicInteger count = new AtomicInteger();

        Thread thread1 = new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (count.get() < 30) {
                synchronized (obj) {
                    if (count.get() % 3 == 0) {
                        if (count.get() < 30) {
                            for (int i = 0; i < 5; i++) {
                                System.out.println("AA");
                            }
                            count.incrementAndGet();
                            obj.notifyAll();
                        }
                    }
                    if (count.get() < 28) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (count.get() < 30) {
                synchronized (obj) {
                    if (count.get() % 3 == 1) {
                        if (count.get() < 30) {
                            for (int i = 0; i < 10; i++) {
                                System.out.println("BB");
                            }
                            count.incrementAndGet();
                            obj.notifyAll();
                        }
                    }
                    if (count.get() < 29) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (count.get() < 30) {
                synchronized (obj) {
                    if (count.get() % 3 == 2) {
                        if (count.get() < 30) {
                            for (int i = 0; i < 15; i++) {
                                System.out.println("CC");
                            }
                            System.out.println("第 " + (count.get() / 3) + " 轮");
                            count.incrementAndGet();
                            obj.notifyAll();
                        }
                    }
                    if (count.get() < 30) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        latch.countDown();
    }


    /**
     * 使用 lock + condition
     */
    private static void testOne() {

        CountDownLatch latch = new CountDownLatch(1);

        Lock lock = new ReentrantLock();

        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        final AtomicInteger count = new AtomicInteger();

        Thread thread1 = new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (count.get() < 28) {
                try {
                    lock.lock();
                    if (count.get() % 3 == 0) {
                        if (count.get() < 30) {
                            System.out.println("第 " + (count.get() / 3) + " 轮");
                            for (int i = 0; i < 5; i++) {
                                System.out.println("AA");
                            }
                            count.incrementAndGet();
                            condition2.signal();
                        }
                    }

                    if (count.get() < 28) {
                        condition1.await();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (count.get() < 29) {
                try {
                    lock.lock();
                    if (count.get() % 3 == 1) {
                        if (count.get() < 30) {
                            for (int i = 0; i < 10; i++) {
                                System.out.println("BB");
                            }
                            count.incrementAndGet();
                            condition3.signal();
                        }
                    }

                    if (count.get() < 29) {
                        condition2.await();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (count.get() < 30) {
                try {
                    lock.lock();
                    if (count.get() % 3 == 2) {
                        if (count.get() < 30) {
                            for (int i = 0; i < 15; i++) {
                                System.out.println("CC");
                            }
                            count.incrementAndGet();
                            condition1.signal();
                        }
                    }

                    if (count.get() < 30) {
                        condition3.await();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        latch.countDown();
    }
}
