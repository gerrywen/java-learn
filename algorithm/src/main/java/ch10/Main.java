package ch10;

import base.file.FileInfo;
import base.file.Files;
import ch10.map.Map;
import ch10.map.TreeMap;
import ch10.set.Set;
import ch10.set.TreeSet;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/22 0022 16:19
 */
public class Main {


    public static void main(String[] args) {
//        test1();
//        test2();
        test3();

        // 源码解读
        new java.util.TreeMap<>();
    }

    static void test1() {
        Map<String, Integer> map = new TreeMap<>();
        map.put("c", 2);
        map.put("a", 5);
        map.put("a", 9);
        map.put("b", 6);
        map.put("a", 8);

        map.traversal(new Map.Visitor<String, Integer>() {
            public boolean visit(String key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }


    static void test2() {
        FileInfo fileInfo = Files.read("G:\\java\\java-learn\\algorithm\\src\\main\\java",
                new String[]{"java"});

        System.out.println("文件数量：" + fileInfo.getFiles());
        System.out.println("代码行数：" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词数量：" + words.length);

        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < words.length; i++) {
            Integer count = map.get(words[i]);
            count = (count == null) ? 1 : (count + 1);
            map.put(words[i], count);
        }

        map.traversal(new Map.Visitor<String, Integer>() {
            public boolean visit(String key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    static void test3() {
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(12);
        treeSet.add(10);
        treeSet.add(7);
        treeSet.add(11);
        treeSet.add(11);
        treeSet.add(10);
        treeSet.add(10);
        treeSet.add(9);
        treeSet.add(18);
        treeSet.add(18);
        treeSet.add(11);

        treeSet.traversal(new Set.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }
}
