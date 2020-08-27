package com.lushwe.common.obj;

import lombok.Data;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 说明：UserInfo对象
 *
 * @author Jack Liu
 * @date 2019-09-06 09:24
 * @since 0.1
 */
@Data
public class UserInfo implements Externalizable {

    private Long id;
    private String name;
    private int age;

    public static String value1 = "静态";
    public transient String value2 = "临时";

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (Long) in.readObject();
        name = (String) in.readObject();
        age = in.readInt();
    }
}
