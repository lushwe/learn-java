package com.lushwe.feign.remote;

import feign.Param;
import feign.RequestLine;

/**
 * 说明：Feign接口服务
 *
 * @author Jack Liu
 * @date 2020-09-29 21:13
 * @since 0.1
 */
public interface FeignUserService {

    /**
     * 根据用户ID获取用户姓名
     *
     * @param id
     * @return
     */
    @RequestLine("GET /users/getUserName?id={id}")
    String getUserName(@Param("id") Long id);

}
