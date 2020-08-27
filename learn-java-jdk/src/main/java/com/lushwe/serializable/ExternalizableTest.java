package com.lushwe.serializable;

import com.alibaba.fastjson.JSON;
import com.lushwe.common.obj.UserInfo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 说明：序列化&反序列化测试
 *
 * @author Jack Liu
 * @date 2019-09-06 09:24
 * @since 0.1
 */
public class ExternalizableTest {

    private static final String filePathName = "/Users/Dev/serialize/UserInfo.txt";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        UserInfo user = new UserInfo();
        user.setId(1L);
        user.setName("张san");
        user.setAge(24);
        System.out.println("序列化前: " + JSON.toJSONString(user));
        System.out.println("序列化前: value1 " + UserInfo.value1);
        System.out.println("序列化前: value2 " + user.value2);
        writeObject(user);

        UserInfo.value1 = "静态1";

        UserInfo u = readObject();
        System.out.println("反序列化后: " + JSON.toJSONString(u));
        System.out.println("反序列化后: value1 " + UserInfo.value1);
        System.out.println("反序列化后: value2 " + u.value2);
    }

    private static UserInfo readObject() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePathName));
        UserInfo u = (UserInfo) ois.readObject();
        System.out.println("反序列化完成");

        return u;
    }

    private static void writeObject(UserInfo user) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePathName));
        oos.writeObject(user);
        oos.flush();
        oos.close();
        System.out.println("序列化完成");
    }
}
