package com.lushwe.lang.ref;

/**
 * 说明：回收测试对象
 *
 * @author Jack Liu
 * @date 2020-03-18 21:54
 * @since 0.1
 */
public class Student {

    @Override
    protected void finalize() throws Throwable {

        System.out.println("Student 被回收了");
    }
}
