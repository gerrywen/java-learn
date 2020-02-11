package ch04;

import base.List;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/11 0011 19:13
 */
public class Stack<E> {
    private List<E> list = new ArrayList<>();

    public void clear() {
        list.clear();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E element) {
        list.add(element);
    }

    public E pop() {
        return list.remove(list.size() - 1);
    }

    public E top() {
        return list.get(list.size() -1);
    }
}
