package ch07;

import base.printer.BinaryTrees;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/2/19 10:11
 */
public class Main {
    public static void main(String[] args) {
        test1();
    }

    static void test1() {
        Integer[] data = new Integer[]{
                67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39
        };
        AVLTree<Integer> avl = new AVLTree<>();
        for (Integer datum : data) {
            avl.add(datum);
        }
        BinaryTrees.println(avl);
        System.out.println("---------------------------------------");

        for (Integer datum : data) {
            avl.remove(datum);
            System.out.println("【" + datum + "】");
            BinaryTrees.println(avl);
            System.out.println("---------------------------------------");
        }
    }


}
