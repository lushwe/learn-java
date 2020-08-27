package com.lushwe.common.obj;

import lombok.Data;

import java.io.Serializable;

/**
 * 说明：User对象
 *
 * @author Jack Liu
 * @date 2019-09-06 09:24
 * @since 0.1
 */
@Data
public class User implements Serializable {

    private Long id;
    private String name;
    private int age;
    private String remark;

    public static String value1 = "静态";
    public transient String value2 = "临时";

}
