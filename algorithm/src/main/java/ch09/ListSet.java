package ch09;

import base.List;
import ch05.LinkedList;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/22 0022 13:15
 */
public class ListSet<E> implements Set<E> {

    // 链表
    private List<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        // 获取元素位置
        int index = list.indexOf(element);
        if (index != List.ELEMENT_NOT_FOUND) {// 存在就覆盖
            list.set(index, element);
        } else { // 不存在就添加
            list.add(element);
        }
    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if (index != List.ELEMENT_NOT_FOUND) {
            list.remove(index);
        }
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) return;

        int size = list.size();
        for (int i = 0; i < size; ++i) {
            if (visitor.visit(list.get(i))) return;
        }
    }
}