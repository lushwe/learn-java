package com.lushwe.guava.hash;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * 说明：布隆过滤器测试
 *
 * @author Jack Liu
 * @date 2020-09-11 17:07
 * @since 0.1
 */
public class BloomFilterTest {

    public static void main(String[] args) {

        // 期望插入的数量
        int expectedInsertions = 1000000;

        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), expectedInsertions);

        Set<String> sets = new HashSet<>(expectedInsertions);
        List<String> lists = new ArrayList<>(expectedInsertions);

        // 初始化
        for (int i = 0; i < expectedInsertions; i++) {
            String uuid = UUID.randomUUID().toString();
            bloomFilter.put(uuid);
            sets.add(uuid);
            lists.add(uuid);
        }

        int right = 0; // 正确判断的次数
        int wrong = 0; // 错误判断的次数

        for (int i = 0; i < 10000; i++) {

            String data = i % 100 == 0 ? lists.get(i) : UUID.randomUUID().toString();

            if (bloomFilter.mightContain(data)) {
                if (sets.contains(data)) {
                    right++;
                    continue;
                }
                wrong++;
            }
        }

        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(4);
        float percent = (float) wrong / 9900;
        float bingo = (float) (9900 - wrong) / 9900;

        System.out.println("在100W个元素中，判断100个实际存在的元素，布隆过滤器认为存在的：" + right);
        System.out.println("在100W个元素中，判断9900个实际不存在的元素，误认为存在的：" + wrong);
        System.out.println("命中率：" + percentFormat.format(bingo) + "，误判率：" + percentFormat.format(percent));
    }
}
