package ch09;

import utils.TimeUtils;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/22 0022 13:20
 */
public class Main {

    public static void main(String[] args) {
//        test1();
        test2();
    }


    private static void test2() {
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
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    private static void test1() {
        Set<Integer> listSet = new ListSet<>();
        listSet.add(10);
        listSet.add(11);
        listSet.add(11);
        listSet.add(12);
        listSet.add(10);

        listSet.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }


    static void testSet(Set<String> set, String[] words) {
        for (int i = 0; i < words.length; i++) {
            set.add(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            set.contains(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            set.remove(words[i]);
        }
    }
}
