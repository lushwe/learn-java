package com.lushwe;

/**
 * 说明：接口方法默认实现（为了兼容历史实现）
 *
 * @author Jack Liu
 * @date 2020-08-12 14:15
 * @since 0.1
 */
public interface MyInterface {


    void run();

    default void test() {
        // do nothing
    }
}
