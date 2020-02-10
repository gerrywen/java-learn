package ch03.single;

import base.AbstractList;

public class SingleLinkedList<E> extends AbstractList<E> {

    /**
     * 第一个Node对象
     */
    private Node<E> first;

    /**
     * 元素对象
     * @param <E>
     */
    private static class Node<E> {
        // 增加元素
        E element;
        // 下一个对象
        Node<E> next;

        public Node(E element, Node<E> node) {
            this.element = element;
            this.next = node;
        }
    }

    /**
     * 清楚元素
     */
    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    /**
     * 获取元素
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        /*
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        return node(index).element;
    }

    /**
     * 根据索引更新元素值
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        // 获取原本node
        Node<E> node = node(index);
        // 旧元素
        E old = node.element;
        // 新元素赋值
        node.element = element;
        return old;
    }

    /**
     * 添加元素
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        /*
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        rangeCheckForAdd(index);

        if (index == 0) {
            // 第一个元素next就是null
            first = new Node<>(element, first);
        } else {
            // 0 1 2
            // 获取当前上一个node 相当于0位置node
            Node<E> prev = node(index - 1);
            // 获取当前下一个node 相当于1位置node
            Node<E> next = prev.next;

            // 假如该新的对象插入到0后面
            // prev.next 相当于 0 , 这时候next 相当于2
            prev.next = new Node<>(element, next);
        }
        size++;

    }

    /**
     * 移除元素
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);

        // 记录第一个node
        Node<E> node = first;
        if (index == 0) {
            // 相当于第一个node设置为空
            first = first.next;
        } else {
            // 0 1 2
            // 获取当前上一个node 相当于0位置node
            Node<E> prev = node(index - 1);
            // 获取当前下一个node 相当于2位置node
            prev.next = node(index).next;
        }
        size--;
        return node.element;
    }

    /**
     * 根据元素查找node位置
     * @param element
     * @return
     */
    @Override
    public int indexOf(E element) {
        // 取第一个node
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; ++i) {
                if (node.element == null) return i;
                // 取下一个node
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; ++i) {
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 根据索引获取node
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        // 检查元素是否越界
        rangeCheck(index);

        // 第一个node
        Node<E> node = first;
        // 遍历一直取下一个next
        for (int i = 0; i < index; ++i) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(node.element);

            node = node.next;
        }
        string.append("]");

//		Node<E> node1 = first;
//		while (node1 != null) {
//
//
//			node1 = node1.next;
//		}
        return string.toString();
    }
}
