package com.lushwe.guava.collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 说明：数组测试
 *
 * @author Jack Liu
 * @date 2019-12-24 16:03
 * @since 0.1
 */
public class ArrayTest {

    public static void main(String[] args) {


        Integer[] arr = {1, 2, 3};

        List<Integer> arrayList = Lists.newArrayList(arr);

        System.out.println(JSON.toJSONString(arrayList));

        arrayList.add(4);
        arrayList.add(5);

        System.out.println(JSON.toJSONString(arrayList));


        Object[] integers = arrayList.toArray();
        System.out.println(JSON.toJSONString(integers));


        Integer[] integers1 = arrayList.toArray(new Integer[0]);
        System.out.println(JSON.toJSONString(integers1));

    }
}
