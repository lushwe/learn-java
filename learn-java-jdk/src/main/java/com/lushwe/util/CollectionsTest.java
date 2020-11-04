package com.lushwe.util;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 说明：Collections使用测试
 *
 * @author Jack Liu
 * @date 2020-08-04 15:24
 * @since 0.1
 */
public class CollectionsTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        System.out.println("测试 reverse");
        reverse(list);

        System.out.println("测试 shuffle");
        shuffle(list);
    }

    /**
     * 反转列表
     *
     * @param list
     */
    public static void reverse(List<String> list) {
        System.out.println(JSON.toJSONString(list, true));
        Collections.reverse(list);
        System.out.println(JSON.toJSONString(list, true));
    }

    /**
     * 打乱列表
     *
     * @param list
     */
    public static void shuffle(List<String> list) {
        System.out.println(JSON.toJSONString(list, true));
        Collections.shuffle(list);
        System.out.println(JSON.toJSONString(list, true));
    }
}
