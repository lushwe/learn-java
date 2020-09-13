package com.lushwe.hutool;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.math.Money;

/**
 * 说明：HuTool工具类测试
 *
 * @author Jack Liu
 * @date 2020-09-10 18:03
 * @since 0.1
 */
public class HuToolTest {

    public static void main(String[] args) {

        DateTime dateTime = DateUtil.date();
        System.out.println(dateTime);


        Money money = new Money(100L, 100);
        System.out.println(money);
        System.out.println(money.getCent());
    }
}
