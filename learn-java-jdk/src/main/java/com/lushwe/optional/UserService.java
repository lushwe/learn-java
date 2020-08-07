package com.lushwe.optional;

import java.util.Objects;
import java.util.Optional;

/**
 * 说明：Optional 返回
 *
 * @author Jack Liu
 * @date 2019/4/16 下午3:32
 * @since 1.0
 */
public class UserService {

    public Optional<String> getUserNameByUserId(Long userId) {
        if (Objects.isNull(userId)) {
            return Optional.empty();
        }
        return Optional.of("zhangsan");
    }

    public String getUserName(Long userId) {

        User user = new User();

        Optional<User> optionalUser = Optional.ofNullable(user);

        Optional<String> optionalS = optionalUser.map(p -> p.getName());

        String s = optionalS.orElse("");

        Optional<String> optional = getUserNameByUserId(userId);

        optional.get();

        return optional.orElse("");
    }
}
