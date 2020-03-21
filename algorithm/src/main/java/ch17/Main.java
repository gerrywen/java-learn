package ch17;

import base.printer.BinaryTreeInfo;
import base.printer.BinaryTrees;
import base.tree.BST;

import java.util.Comparator;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/15 0015 18:25
 */
public class Main {

    /**
     * e1 > e2
     */
    private static class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    /**
     * e1 < e2
     */
    private static class PersonComparator2 implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }


    public static void main(String[] args) {
//        test4();
//        test1();
//        test3();
//        test7();
        test8();
    }


    static void test1() {
        Integer[] data = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        for (Integer datum : data) {
            bst1.add(datum);
        }

        BinaryTrees.println(bst1);
    }

    static void test3() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < 40; i++) {
            bst.add((int)(Math.random() * 100));
        }

        BinaryTrees.println(bst);
    }

    static void test4() {

        /**
         * Java的匿名类，类似于iOS中的Block、JS中的闭包（function）
         */
        BinarySearchTree<Person> bst0 = new BinarySearchTree<>((Person e1, Person e2) -> {
            return e1.getAge() - e2.getAge();
        });
        bst0.add(new Person(12));
        bst0.add(new Person(15));

        System.out.println("");
        BinaryTrees.println(bst0);

        BinarySearchTree<Person> bst1 = new BinarySearchTree<>();
        bst1.add(new Person(12));
        bst1.add(new Person(15));

        System.out.println("");
        BinaryTrees.println(bst1);

        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator());
        bst2.add(new Person(12));
        bst2.add(new Person(15));

        System.out.println("");
        BinaryTrees.println(bst2);
    }

    static void test6() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (Integer datum : data) {
            bst.add(datum);
        }

//        bst.levelOrderTraversal();
        bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });

        bst.preorder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });


        bst.inorder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });

        bst.postorder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }
    static void test10() {
        BinaryTrees.println(new BinaryTreeInfo() {

            @Override
            public Object string(Object node) {
                return node.toString() + "_";
            }

            @Override
            public Object root() {
                return "A";
            }

            @Override
            public Object right(Object node) {
                if (node.equals("A")) return "C";
                if (node.equals("C")) return "E";
                return null;
            }

            @Override
            public Object left(Object node) {
                if (node.equals("A")) return "B";
                if (node.equals("C")) return "D";
                return null;
            }
        });
    }

    static void test7() {
        Integer[] data = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (Integer datum : data) {
            bst.add(datum);
        }

        BinaryTrees.println(bst);

//        bst.remove(7);
        bst.remove(2);

        BinaryTrees.println(bst);
    }

    static void test8() {
        Integer[] data = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BST<Integer> bst = new BST<>();
        for (Integer datum : data) {
            bst.add(datum);
        }

        BinaryTrees.println(bst);

//        bst.remove(7);
        bst.remove(2);
        bst.remove(7);

        BinaryTrees.println(bst);
    }
}
