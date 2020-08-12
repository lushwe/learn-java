package com.lushwe.array;

import java.util.Arrays;
import java.util.List;

/**
 * 说明：数组使用学习
 *
 * @author Jack Liu
 * @date 2019-12-24 15:53
 * @since 0.1
 */
public class ArrayTest {

    public static void main(String[] args) {
//        int[] myArray = {1, 2, 3};
        Integer[] myArray = {1, 2, 3};
        List myList = Arrays.asList(myArray);
        //1
        System.out.println(myList.size());
        //数组地址值
        System.out.println(myList.get(0));
        //报错：ArrayIndexOutOfBoundsException
        System.out.println(myList.get(1));
        int[] array = (int[]) myList.get(0);
        //1
        System.out.println(array[0]);
    }
}
