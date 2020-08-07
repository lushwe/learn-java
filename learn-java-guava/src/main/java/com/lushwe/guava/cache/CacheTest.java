package com.lushwe.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 说明：缓存测试
 *
 * @author Jack Liu
 * @date 2019-12-24 17:10
 * @since 0.1
 */
public class CacheTest {


    public static void main(String[] args) {

        // 堆缓存

        Cache<String, String> myCache = CacheBuilder.newBuilder()
                .concurrencyLevel(4)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(10000)
                .build();
    }
}
