package com.lushwe.thread;

/**
 * 说明：可继承的线程本地上下文
 *
 * @author Jack Liu
 * @date 2020-01-18 11:36
 * @since 0.1b
 */
public class InheritableThreadLocalContext {

    private static ThreadLocal<Object> inheritableThreadLocal = new InheritableThreadLocal();

    public static void set(Object t) {
        inheritableThreadLocal.set(t);
    }

    public static <T> T get() {
        return (T) inheritableThreadLocal.get();
    }

    public static void remove() {
        inheritableThreadLocal.remove();
    }
}
