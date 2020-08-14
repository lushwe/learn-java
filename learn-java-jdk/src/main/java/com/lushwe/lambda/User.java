package com.lushwe.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


/**
 * @author Jack Liu
 * @date 2018/8/7 19:34
 */
@Data
@ToString
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private Integer age;
}
