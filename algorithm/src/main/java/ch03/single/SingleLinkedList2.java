package ch03.single;

import base.AbstractList;

public class SingleLinkedList2<E> extends AbstractList<E> {

    /**
     * 创建第一个空node
     */
    private Node<E> first;

    /**
     * 无参数构造法
     */
    public SingleLinkedList2() {
        // 增加一个虚拟头结点
        first = new Node<>(null, null);
    }

    /**
     * 创建node
     * @param <E>
     */
    private static class Node<E> {
        E element;
        Node<E> next;
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    /**
     * 清除元素
     */
    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    /**
     * 根据索引获取元素
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        return node(index).element;
    }

    /**
     * 根据索引更新元素
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        // 旧元素
        E old = node.element;
        // 新元素赋值
        node.element = element;
        return old;
    }

    /**
     * 插入元素
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        // 获取前一个node
        Node<E> prev = index == 0 ? first : node(index - 1);
        // 获取原本位置的元素
        Node<E> next = prev.next;
        // 当前插入位置的元素
        prev.next = new Node<>(element, next);
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

        // 获取前一个node
        Node<E> prev = index == 0 ? first : node(index - 1);
        // 被移除的node
        Node<E> old = prev.next;
        // 获取下一个node
        prev.next = old.next;

        size--;
        return old.element;
    }

    /**
     * 获取元素的位置
     * @param element
     * @return
     */
    @Override
    public int indexOf(E element) {
        // 获取虚拟头后面元素开始获取
        Node<E> node = first.next;

        if (element == null) {
            for (int i = 0 ; i < size; ++i) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; ++i ){
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 获取index位置对应的节点对象
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);

        // 获取虚拟头的next
        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        Node<E> node = first.next;
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
