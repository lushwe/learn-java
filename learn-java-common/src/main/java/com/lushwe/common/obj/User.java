package com.lushwe.common.obj;

import lombok.Data;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 说明：User对象
 *
 * @author Jack Liu
 * @date 2019-09-06 09:24
 * @since 0.1
 */
@Data
public class User extends BaseUser {

    private Long id;
    private String name;
    private int age;
    private String remark;

    public static String value1 = "静态变量";
    public transient String value2 = "瞬态变量";

//    private void writeObject(ObjectOutputStream oos) throws IOException {
//        throw new NotSerializableException("[" + this.getClass().getName() + "]不支持序列化");
//    }

//    private void readObject(ObjectInputStream ois) throws IOException {
//        throw new NotSerializableException("[" + this.getClass().getName() + "]不支持反序列化");
//    }

}
