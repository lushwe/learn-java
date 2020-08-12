package com.lushwe.lang;

/**
 * String类方法测试
 */
public class StringTest {

    public static void main(String[] args) {
        // 占位符：字符串用%s，数字用%d
        System.out.println(String.format("其实%d不行，%s", 999, "ok"));

        // 测试 format 方法（占位符多了的话会报错）
        System.out.println(String.format("测试%s测%s试", "1"));
    }
}
