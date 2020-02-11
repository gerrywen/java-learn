package ch05;

import base.List;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/11 0011 19:31
 */
public class Queue<E> {
    private List<E> list = new LinkedList<E>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    public void enQueue(E element) {
        list.add(element);
    }

    public E deQueue() {
        return list.remove(0);
    }

    public E front() {
        return list.get(0);
    }
}
