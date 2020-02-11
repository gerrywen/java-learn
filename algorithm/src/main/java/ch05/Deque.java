package ch05;

import base.List;

/**
 * @author wenguoli
 * @description: 双端队列
 * @date 2020/2/11 0011 19:36
 */
public class Deque<E> {
    private List<E> list = new LinkedList<>();

    public int size(){
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    /**
     * 从队尾入队
     * @param element
     */
    public void enQueueRear(E element) {
        list.add(element);
    }

    /**
     * 从队头出队
     * @return
     */
    public E deQueueFront() {
        return list.remove(0);
    }

    /**
     *
     * @param element
     */
    public void enQueueFront(E element) {
        list.add(0, element);
    }


    /**
     * 从队尾出队
     * @return
     */
    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    public E front() {
        return list.get(0);
    }

    public E rear() {
        return list.get(list.size() - 1);
    }
}
