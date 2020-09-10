package com.lushwe.lang;

/**
 * String类方法测试
 */
public class StringTest {

    public static void main(String[] args) {
        testFormat();
//        testSubstring();
    }

    private static void testFormat() {
        // 占位符：字符串用%s，数字用%d
        System.out.println(String.format("其实%d不行，%s", 999, "ok"));


        System.out.println(String.format("测试测试", "1", "2"));
        System.out.println(String.format("测试%s测试", "1", "2"));
        System.out.println(String.format("测试%s测%s试", "1"));

        // 总结
        // format方法，占位符少了能正常运行，但是多了就会报错
    }

    private static void testSubstring() {

        String s1 = "abc";
        String s2 = s1.substring(0, 1);

        System.out.println(s1);
        System.out.println(s2);

        // 总结
        // substring方法并不会改变字符串s1的值，只是会重新创建一个字符串s2
    }
}
