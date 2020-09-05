package com.lushwe.thread;

/**
 * 说明：线程 interrupt 测试
 *
 * @author Jack Liu
 * @date 2020-05-09 10:50
 * @since 0.1
 */
public class ThreadInterruptTest {

    public static void main(String[] args) {

//        test();
//        testInterrupt();
//        testInterruptWithSleep();

    }


    public static void testInterrupt() {
        Thread t = new Thread(() -> {
            int i = 0;
            for (; ; ) {
                System.out.println("第" + (i++) + "次, " + Thread.currentThread().isInterrupted());
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("第" + (i++) + "次, " + Thread.currentThread().isInterrupted());
                    break;
                }
            }
        });
        t.start();
        try {
            Thread.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();

        // 总结：
        // 调用线程的 interrupt() 方法，中断标志位被置为 true，但是程序仍然继续执行
    }

    public static void testInterruptWithSleep() {
        Thread t = new Thread(() -> {
            int i = 0;
            for (; ; ) {
                try {
                    Thread.sleep(100L);
                    System.out.println("第" + (i++) + "次, " + Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    System.out.println("第" + (i++) + "次, 出现异常, " + Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                    break;
                }
            }
        });
        t.start();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();

        // 总结：
        // 调用线程的 interrupt() 方法，中断标志位被置为 true，但是程序仍然继续执行
        // 当线程在 sleep 时，调用 interrupt() 方法，sleep() 方法会抛出 InterruptedException 异常，并清除中断标志位
    }

    public static void test() {
        System.out.println("Main thread started.");

        Thread t = new Thread(new Worker());
        t.start();

        try {
            Thread.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.interrupt();
        System.out.println("Main thread stopped.");

        // 总结：
        // 调用 interrupt() 方法，会设置中断标志位
        // isInterrupted() 方法用于判断中断标志位
        // interrupted() 方法用于判断中断位，并清除中断标志位
    }


    public static class Worker implements Runnable {

        @Override
        public void run() {
            System.out.println("Worker started.");
            boolean f;
            int i = 0;
            Thread c = Thread.currentThread();
            System.out.println("while之前线程中断状态isInterrupted()：" + c.isInterrupted());
            while (!(f = Thread.interrupted())) {
                System.out.println("while内，还没中断，f 返回值为：" + f);
                System.out.println(c.getName() + "  " + i++ + "  " + c.isInterrupted());
            }
            System.out.println("跳出循环 f 返回值：" + f);
            System.out.println("跳出循环 c.isInterrupted() 返回值: " + c.isInterrupted());
            System.out.println("跳出循环 Thread.interrupted() 返回值：" + Thread.interrupted());

            c.interrupt();
            System.out.println("再次中断后查询中断状态isInterrupted():" + c.isInterrupted());
            System.out.println("再次中断后查询中断状态isInterrupted():" + c.isInterrupted());
            System.out.println("再次中断后Thread.interrupted() 返回值:" + Thread.interrupted());
            System.out.println("再次中断后查询中断状态isInterrupted():" + c.isInterrupted());

            System.out.println("Worker stopped.");

        }
    }
}
