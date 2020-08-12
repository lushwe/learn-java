package com.lushwe.primitive;

/**
 * 说明：原始基本类型数据测试
 *
 * @author Jack Liu
 * @date 2020-07-30 15:15
 * @since 0.1
 */
public class PrimitiveTypeTest {

    public static void main(String[] args) {

        System.out.println(Byte.MAX_VALUE);
        System.out.println(Short.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(Boolean.TRUE);

        long l = 9223372036854775807L;
        String ls = "922 3372 0368 5477 5807";
        String is = "2147483647";

        int fee = 30000000;
        long feeDB = fee * 100L;

        System.out.println(feeDB);

        System.out.println(Byte.valueOf("10").compareTo(Byte.valueOf("1")));
        System.out.println(Short.valueOf("10").compareTo(Short.valueOf("1")));
        System.out.println(Integer.valueOf("10").compareTo(Integer.valueOf("1")));
        System.out.println(Long.valueOf("10").compareTo(Long.valueOf("1")));
    }
}
