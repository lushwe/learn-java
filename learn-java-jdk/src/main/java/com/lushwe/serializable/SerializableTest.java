package com.lushwe.serializable;

import com.alibaba.fastjson.JSON;
import com.lushwe.common.obj.User;

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
public class SerializableTest {

    private static final String filePathName = "/Users/Dev/serialize/user.txt";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        User user = new User();
//        user.setId(1L);
//        user.setName("张san");
//        user.setAge(24);
//        user.setRemark("测试序列化");
//        System.out.println("序列化前: " + JSON.toJSONString(user));
//        System.out.println("序列化前: value1 " + User.value1);
//        System.out.println("序列化前: value2 " + user.value2);
//        writeObject(user);

        // 静态变量属于类，不会被序列化
//        User.value1 = "静态1";

        User u = readObject();
        System.out.println("反序列化后: " + JSON.toJSONString(u));
        System.out.println("反序列化后: value1 " + User.value1);
        System.out.println("反序列化后: value2 " + u.value2);
    }

    private static User readObject() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePathName));
        User u = (User) ois.readObject();
        System.out.println("反序列化完成");

        return u;
    }

    private static void writeObject(User user) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePathName));
        oos.writeObject(user);
        oos.flush();
        oos.close();
        System.out.println("序列化完成");
    }
}
