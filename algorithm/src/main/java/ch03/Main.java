package ch03;

import base.List;
import ch03.circle.CircleLinkedList;
import ch03.circle.CircleLinkedList2;
import ch03.circle.SingleCircleLinkedList;
import ch03.single.SingleLinkedList;
import ch03.single.SingleLinkedList2;
import utils.Asserts;

public class Main {
    public static void main(String[] args) {

//        testList(new SingleLinkedList<>());
//        testList(new SingleLinkedList2<>());
//        testList(new LinkedList<>());
//        new java.util.LinkedList<>();
//        new java.util.ArrayList<>();
//        testList(new SingleCircleLinkedList<>());
//        testList(new CircleLinkedList<>());
//        testList(new CircleLinkedList2<>());
//        josephus();
    }

    static void josephus() {
        CircleLinkedList2<Integer> list = new CircleLinkedList2<>();
        for (int i = 1; i <= 8; i++) {
            list.add(i);
        }

        // 指向头结点（指向1）
        list.reset();

        while (!list.isEmpty()) {
            list.next();
            list.next();
            System.out.println(list.remove());
        }
    }
    /**
     * 链表测试
     * @param list
     */
    static void testList(List<Integer> list) {
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        list.add(0, 55); // [55, 11, 22, 33, 44]
        list.add(2, 66); // [55, 11, 66, 22, 33, 44]
        list.add(list.size(), 77); // [55, 11, 66, 22, 33, 44, 77]

        list.remove(0); // [11, 66, 22, 33, 44, 77]
        list.remove(2); // [11, 66, 33, 44, 77]
        list.remove(list.size() - 1); // [11, 66, 33, 44]

        Asserts.test(list.indexOf(44) == 3);
        Asserts.test(list.indexOf(22) == List.ELEMENT_NOT_FOUND);
        Asserts.test(list.contains(33));
        Asserts.test(list.get(0) == 11);
        Asserts.test(list.get(1) == 66);
        Asserts.test(list.get(list.size() - 1) == 44);

        System.out.println(list);
    }

}
