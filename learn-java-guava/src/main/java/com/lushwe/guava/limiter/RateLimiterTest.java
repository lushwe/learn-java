package com.lushwe.guava.limiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 说明：限流测试
 *
 * @author Jack Liu
 * @date 2020-08-26 07:43
 * @since 0.1
 */
public class RateLimiterTest {

    private static ConcurrentHashMap<String, RateLimiter> resourceRateLimiter = new ConcurrentHashMap<>();

    static {
        resourceRateLimiter.putIfAbsent("order", RateLimiter.create(100));
    }

    public static void main(String[] args) {
        final AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                if (resourceRateLimiter.get("order").tryAcquire(1, TimeUnit.MILLISECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " 获得资源，执行业务逻辑, count=" + count.getAndIncrement());
                } else {
                    System.out.println(Thread.currentThread().getName() + " 限流");
                }
            }).start();
        }
    }
}
