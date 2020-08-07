package com.lushwe.extend;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：继承测试
 *
 * @author Jack Liu
 * @date 2020-08-07 16:27
 * @since 0.1
 */
public class ExtendsTest {

    public static void main(String[] args) {

        List<Person> personList = new ArrayList<>();
        personList.add(new Jack());
        personList.add(new Tom());

        for (Person person : personList) {
            if (person instanceof Son) {
                Son son = (Son) person;
                System.out.println("son.getParent()=" + son.getParent() + ", son.getName()=" + son.getName());
            }
            if (person instanceof Father) {
                Father father = (Father) person;
                System.out.println("father.getSon()=" + father.getSon() + ", father.getName()=" + father.getName());
            }
        }
    }
}


class Jack implements Son {

    @Override
    public String getParent() {
        return "Tom";
    }

    @Override
    public String getName() {
        return "Jack";
    }
}


class Tom implements Father {

    @Override
    public String getName() {
        return "Tome";
    }

    @Override
    public String getSon() {
        return "Jack";
    }
}