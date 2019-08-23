package g.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {


    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
    }

    private static List stringList() {
        List<String> stringList = new ArrayList<>();
        stringList.add("ddd2");
        stringList.add("aaa2");
        stringList.add("bbb1");
        stringList.add("aaa1");
        stringList.add("bbb3");
        stringList.add("ccc");
        stringList.add("bbb2");
        stringList.add("ddd1");
        return stringList;
    }

    private static void test1() {
        List<String> list = stringList();
//        System.out.println(list);
        list.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
    }

    private static void test2() {
        List<String> list = stringList();
        list.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);
        // 需要注意的是，排序只创建了一个排列好后的Stream，而不会影响原有的数据源，排序之后原数据stringCollection是不会被修改的：
        System.out.println(list);
    }

    private static void test3() {
        List<String> list = stringList();
        list.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);
    }


    private static void test4() {
        List<String> list = stringList();
        boolean anyStartsWithA = list.stream().anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartsWithA);

        boolean allStartsWithA = list.stream().allMatch(s -> s.startsWith("a"));
        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ = list.stream().noneMatch(s -> s.startsWith("z"));
        System.out.println(noneStartsWithZ);      // true
    }

    private static void test5() {
        List<String> list = stringList();

        long startsWithB = list.stream().filter(s -> s.startsWith("b")).count();
        System.out.println(startsWithB);    // 3
    }


    private static void test6() {
        List<String> list = stringList();

        Optional<String> reduced = list.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);
    }



}
