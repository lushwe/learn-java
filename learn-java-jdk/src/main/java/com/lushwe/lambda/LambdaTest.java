package com.lushwe.lambda;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Jack Liu
 * @date 2018/8/7 19:34
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class LambdaTest {

    private List<User> users;

    @Before
    public void doBefore() {

        users = new ArrayList<>();
        users.add(new User(1L, "张三", 24));
        users.add(new User(5L, "张三", 60));

        users.add(new User(2L, "李四", 36));
        users.add(new User(6L, "李四", 48));

        users.add(new User(3L, "王五", 48));
        users.add(new User(7L, "王五", 36));

        users.add(new User(4L, "赵六", 60));
        users.add(new User(8L, "赵六", 24));
    }

    /**
     * 遍历列表
     */
    @Test
    public void testForEach() {

        // for循环
        System.out.println("for循环遍历");
        for (User user : users) {
            System.out.println(user);
        }

        // lambda表达式
        System.out.println("lambda表达式遍历");
        users.stream().forEach(e -> System.out.println(e));

    }

    /**
     * lambda表达式使用：<br>
     * 2、匿名内部类简化
     */
    @Test
    public void testInnerClass() {
        // 匿名内部类
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类");
            }
        });
        // Lambda表达式
        Thread thread1 = new Thread(() -> System.out.println("Lambda表达式"));

        thread.start();
        thread1.start();
    }

    /**
     * 测试 sorted
     */
    @Test
    public void testSorted() {

        System.out.println("排序前");
        users.stream().forEach(e -> System.out.println(e));

        System.out.println("排序后，正序");
        users.stream()
                .sorted(Comparator.comparingLong(User::getId))
                .forEach(e -> System.out.println(e));

        System.out.println("排序后，倒叙");
        users.stream()
                .sorted(Comparator.comparingLong(User::getId).reversed())
                .forEach(e -> System.out.println(e));
    }


    /**
     * 测试 List 转 Map
     */
    @Test
    public void testListToMap() {

//        Map<Long, String> map = users.stream().collect(Collectors.toMap(User::getId, User::getName));

        Map<String, Integer> map2 = users.stream().collect(Collectors.toMap(User::getName, User::getAge, (o1, o2) -> o1));
        Map<String, Integer> map3 = users.stream().collect(Collectors.toMap(User::getName, User::getAge, (o1, o2) -> o2));

//        System.out.println("map = " + JSON.toJSONString(map));
        System.out.println("map2 = " + JSON.toJSONString(map2));
        System.out.println("map3 = " + JSON.toJSONString(map3));

        // 说明：(o1, o2) -> o1
        // 当 key存在重复情况，如果没有该 merge 函数，则报错
        // (o1, o2) -> o1 表示：如果有重复的 key，不会覆盖
        // (o1, o2) -> o2 表示：如果有重复的 key，会覆盖


        // 测试 分组
        Map<String, List<User>> userListMap = users.stream().collect(Collectors.groupingBy(User::getName));
        System.out.println("userListMap = " + JSON.toJSONString(userListMap, true));
    }


    /**
     * 测试分隔 joining
     */
    @Test
    public void caseJoining() {


        String s = users.stream()
                .map(e -> e.getName()).collect(Collectors.joining(","));

        System.out.println("s = " + s);

        List<String> asList = Arrays.asList("A", "B", "C");
        String collect = asList.stream().collect(Collectors.joining("||"));
        System.out.println("collect = " + collect);

    }

    /**
     * 测试去重 distinct
     */
    @Test
    public void caseDistinct() {


        List<String> names1 = users.stream()
                .map(User::getName).collect(Collectors.toList());
        List<String> names2 = users.stream()
                .map(User::getName).distinct().collect(Collectors.toList());

        System.out.println("names1 = " + JSON.toJSONString(names1));
        System.out.println("names2 = " + JSON.toJSONString(names2));

    }



    @Test
    public void testMap() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.forEach((p) -> System.out.println("p = " + p));
        System.out.println("列表中每个值加1");
        integers.stream()
                .map(i -> i + 1)
                .forEach((p) -> System.out.println("p = " + p));
    }

    @Test
    public void testFilter() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("a3");
        list.add("a4");
        list.forEach((p) -> System.out.println("p = " + p));
        System.out.println("过滤列表中不含a的字符串");
        list.stream().filter(e -> e.contains("a")).forEach((p) -> System.out.println("p = " + p));
    }

    /**
     * lambda表达式使用：<br>
     * 2、匿名内部类简化
     */
    public static void case3() {

        String[] players1 = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};

        String[] players2 = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};
        //
        Arrays.sort(players1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        // lambda方式
        Arrays.sort(players2, (String s1, String s2) -> s1.compareTo(s2));


    }


    public static void case4() {


        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        List<Person> phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };



        System.out.println("所有程序员的姓名:");
        javaProgrammers.forEach((p) -> System.out.println("javaProgrammers-->" + p));
        phpProgrammers.forEach((p) -> System.out.println("phpProgrammers-->" + p));

        System.out.println("给程序员加薪 5% :");
        Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary());
        javaProgrammers.forEach(giveRaise);
        phpProgrammers.forEach(giveRaise);

        System.out.println("加薪后");
        javaProgrammers.forEach((p) -> System.out.println(p));
        phpProgrammers.forEach((p) -> System.out.println(p));


        System.out.println("下面是月薪超过 $1,400 的PHP程序员:");
        phpProgrammers.stream().filter((p) -> p.getSalary() > 1400).forEach((p) -> System.out.println("p = " + p));

        Predicate<Person> femaleGenderFilter = e -> "female".equals(e.getGender());
        Predicate<Person> ageFilter = e -> e.getAge() > 24;

        System.out.println("女性Java程序员");
        javaProgrammers.stream()
                .filter(femaleGenderFilter)
                .forEach((p) -> System.out.println(p));

        System.out.println("年龄大于24岁的女性Java程序员");
        javaProgrammers.stream()
                .filter(ageFilter)
                .filter(femaleGenderFilter)
                .forEach((p) -> System.out.println(p));


        System.out.println("最前面的3个 Java programmers:");
        javaProgrammers.stream()
                .limit(3)
                .forEach((p) -> System.out.println(p));

        System.out.println("最前面的3个女性 Java programmers:");
        javaProgrammers.stream()
                .filter(femaleGenderFilter)
                .limit(3)
                .forEach((p) -> System.out.println(p));



        System.out.println("1根据 salary 排序 Java programmers:");
        javaProgrammers.stream()
                .sorted((p1, p2) -> p1.getSalary() - p2.getSalary())
                .collect(Collectors.toList())
                .forEach((p) -> System.out.println(p));
        System.out.println("2根据 salary 排序 Java programmers:");
        javaProgrammers.stream()
                .sorted(Comparator.comparingInt(Person::getSalary))
                .collect(Collectors.toList())
                .forEach((p) -> System.out.println(p));

        System.out.println("1工资最低的 Java programmer:");
        Person Minperson = javaProgrammers.stream()
                .min((p1, p2) -> p1.getSalary() - p2.getSalary())
                .get();
        System.out.println(Minperson);

        System.out.println("2工资最低的 Java programmer:");
        Person Minperson1 = javaProgrammers.stream()
                .min(Comparator.comparingInt(Person::getSalary))
                .get();
        System.out.println(Minperson1);


        System.out.println("1工资最高的 Java programmer:");
        Person maxPerson = javaProgrammers.stream()
                .max((p1, p2) -> p1.getSalary() - p2.getSalary())
                .get();
        System.out.println(maxPerson);

        System.out.println("2工资最高的 Java programmer:");
        Person maxPerson1 = javaProgrammers.stream()
                .max(Comparator.comparingInt(Person::getSalary))
                .get();
        System.out.println(maxPerson1);

    }
}
