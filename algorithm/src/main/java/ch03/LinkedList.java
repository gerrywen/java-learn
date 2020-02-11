package ch03;

import base.AbstractList;

/**
 * 双向链表
 * @param <E>
 */
public class LinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    private Node<E> last;

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        /**
         * 有参构造方法
         * @param prev
         * @param element
         * @param next
         */
        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if (prev != null) {
                sb.append(prev.element);
            } else {
                sb.append("null");
            }

            sb.append("_").append(element).append("_");

            if (next != null) {
                sb.append(next.element);
            } else {
                sb.append("null");
            }

            return sb.toString();
        }
    }

    /**
     * 清空元素
     */
    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * 查找元素
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        return node(index).element;
    }

    /**
     * 修改数据
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
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
        rangeCheckForAdd(index);

        if (index == size) {
            // 往后添加,获取最后一个
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, null);
            if (oldLast == null) { // 判断是否为空，说明还没添加元素
                first = last;
            } else {
                oldLast.next = last;
            }
        } else {
            //0 1 2 3 索引为2的node取出来，相当于（3）等下放到next
            // 相当于等下的3
            Node<E> next = node(index);
            // 相当于1
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev, element, next);

            // 下一个的前一个node
            next.prev = node;

            // 判断是否是第一个位置的元素
            if (prev == null) {
                first = node;
            } else {
                prev.next = node;
            }
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
        // 0 1 2 3  比如移除索引2的node
        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        // 假如是第一个元素
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }

        // 假如移除的是最后一个元素
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }

        size--;
        return node.element;
    }

    /**
     * 根据元素查找对应的位置
     * @param element
     * @return
     */
    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;

                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;

                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 根据索引取对应的node
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);

        // 判断索引是否小于size的前一半
        if (index < (size >>1)) {
            Node<E> node = first;
            for (int i = 0; i < index; ++i) {
                node = node.next;
            }
            return node;
        } else {
            // 后半部分向前取值
            Node<E> node = last;
            for (int i = size - 1; i > index; --i) {
                node = node.prev;
            }
            return node;
        }
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
