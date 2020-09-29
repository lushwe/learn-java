package com.lushwe.feign;

import com.lushwe.feign.remote.FeignUserService;
import feign.Feign;
import feign.Request;
import feign.Retryer;

/**
 * 说明：测试Feign接口服务
 *
 * @author Jack Liu
 * @date 2020-09-29 21:18
 * @since 0.1
 */
public class FeignServiceTest {

    public static void main(String[] args) {

        FeignUserService feignUserService = Feign.builder()
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(FeignUserService.class, "http://127.0.0.1:8088");

        String userName = feignUserService.getUserName(1L);
        System.out.println(userName);

    }
}
