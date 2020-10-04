package com.lushwe.common.obj;

import lombok.Data;

import java.io.Serializable;

/**
 * 说明：超类
 *
 * @author Jack Liu
 * @date 2020-08-27 15:13
 * @since 0.1
 */
@Data
public class BaseUser implements Serializable {

    private static final long serialVersionUID = 7075541359384052741L;
    private String baseAddr;
}
