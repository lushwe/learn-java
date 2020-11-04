package com.lushwe.util;

import com.lushwe.optional.User;

import java.util.Optional;

/**
 * 说明：Optional使用测试
 *
 * @author Jack Liu
 * @date 2020-11-04 17:14
 * @since 0.1
 */
public class OptionalTest {

    public static void main(String[] args) {

        User user = null;
        user = new User();
        user.setName("");

        System.out.println("getUserName1 = " + getUserName1(user));
        System.out.println("getUserName2 = " + getUserName2(user));

    }

    /**
     * 获取用户姓名（原始写法）
     *
     * @param user
     * @return
     */
    private static String getUserName1(User user) {
        if (user == null) {
            return null;
        }
        return user.getName();
    }

    /**
     * 获取用户姓名（优雅写法）
     *
     * @param user
     * @return
     */
    private static String getUserName2(User user) {

        return Optional.ofNullable(user).map(User::getName).orElse(null);
    }
}
