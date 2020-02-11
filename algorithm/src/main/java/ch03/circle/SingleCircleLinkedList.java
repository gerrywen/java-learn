package ch03.circle;

import base.AbstractList;
import ch03.single.SingleLinkedList;

import javax.xml.soap.Node;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/11 0011 17:27
 */
public class SingleCircleLinkedList<E> extends AbstractList<E> {

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
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(element).append("_").append(next.element);
            return sb.toString();
        }
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        if (index == 0) {
            // 获取第一个元素
            Node<E> newFirst = new Node<>(element, first);
            // 拿到最后一个节点
            Node<E> last = (size == 0) ? newFirst : node(size - 1);
            // 最后一个链接到第一个
            last.next = newFirst;
            first = newFirst;
        } else {
            // 0 1 2 3   取2
            Node<E> prev = node(index - 1); // 相当于1
            prev.next = new Node<>(element, prev.next);
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

        Node<E> node = first;
        if (index == 0) {
            // 移除第一个元素
            if (size == 1) {
                // 如果只有一个元素，第一个元素设置为空
                first = null;
            } else {
                // 获取最后一个node
                Node<E> last = node(size - 1);
                // 第二个元素相当于第一个元素
                first = first.next;
                // 最后一个元素链接第一个元素
                last.next = first;
            }
        } else {
            // 获取前一个元素
            Node<E> prev = node(index - 1);
            // 即将被移除的元素
            node = prev.next;
            // 重新链接上
            prev.next = node.next;
        }
        size--;
        return node.element;
    }

    /**
     * 根据元素查找位置
     * @param element
     * @return
     */
    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; ++i) {
                if (node.element == null) return i;
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
     * 根据所以找到node
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);

        Node<E> node = first;
        for (int i = 0 ; i < index; ++i) {
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

            string.append(node);

            node = node.next;
        }
        string.append("]");
        return string.toString();
    }
}
