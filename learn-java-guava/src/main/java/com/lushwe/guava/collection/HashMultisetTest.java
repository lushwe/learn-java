package com.lushwe.guava.collection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Arrays;
import java.util.Set;

/**
 * 说明：HashMultiset使用测试
 *
 * @author Jack Liu
 * @date 2020-08-26 10:58
 * @since 0.1
 */
public class HashMultisetTest {

    public static void main(String[] args) {
        HashMultiset<Integer> hashMultiset = HashMultiset.create();

        hashMultiset.add(20200101, 1);
        hashMultiset.add(20200102, 1);
        hashMultiset.addAll(Arrays.asList(20200101, 20200102));
        hashMultiset.addAll(Arrays.asList(20200102, 20200103));

        Set<Multiset.Entry<Integer>> entries = hashMultiset.entrySet();

        for (Multiset.Entry<Integer> entry : entries) {
            System.out.println(entry.getElement() + "=" + entry.getCount());
        }
    }
}
