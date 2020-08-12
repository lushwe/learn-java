package com.lushwe;

/**
 * 说明：对象初始化顺序
 *
 * @author Jack Liu
 * @date 2020-08-05 09:36
 * @since 0.1
 */
public class ObjectInitializeTest {

    public static void main(String[] args) {
        Son son = new Son();

        // 顺序总结
        // 父类的静态属性 -> 子类的静态属性 -> 父类的成员变量 -> 父类的构造方法 -> 子类的成员变量 -> 子类的构造方法
        // 静态变量、静态代码块级别一样，按照代码中出现的顺序执行初始化
        // 多个成员变量按照代码中出现的顺序执行初始化
    }
}


class Person {

    private static int value1 = 1;

    private int value2 = 2;

    static {
        System.out.println("com.lushwe.Person的静态代码块");
        System.out.println("value1=" + value1);
    }

    public Person() {
        System.out.println("com.lushwe.Person的构造方法");
        System.out.println("value2=" + value2);
    }

}

class Son extends Person {

    private static int value7 = 7;

    private int value8 = 8;

    static {
        System.out.println("com.lushwe.Son的静态代码块");
        System.out.println("value7=" + value7);
    }

    public Son() {
        System.out.println("com.lushwe.Son的构造方法");
        System.out.println("value8=" + value8);
    }
}
