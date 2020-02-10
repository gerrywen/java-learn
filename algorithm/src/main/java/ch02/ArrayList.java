package ch02;

import base.AbstractList;

@SuppressWarnings("unchecked")
public class ArrayList<E> extends AbstractList<E> {

    /**
     * 存储所有元素
     */
    private E[] elements;

    /**
     * 默认元素容器大小
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 有参构造
     *
     * @param capaticy
     */
    public ArrayList(int capaticy) {
        // 判断传入的容器是否小于默认指定大小
        capaticy = Math.max(capaticy, DEFAULT_CAPACITY);
        elements = (E[]) new Object[capaticy];
    }

    /**
     * 无参构造
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 清除所有元素
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; ++i) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 获取index位置的元素
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        // 检查索引
        rangeCheck(index);

        return elements[index];
    }

    /**
     * 设置index位置的元素
     *
     * @param index   索引
     * @param element 元素
     * @return
     */
    @Override
    public E set(int index, E element) {
        // 检查索引
        rangeCheck(index);
        // 记录旧的数据
        E oldElement = elements[index];
        // 新元素覆盖
        elements[index] = element;
        return oldElement;
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
        // 添加元素越界判断
        rangeCheckForAdd(index);
        // 自动扩容
        ensureCapacity(index);

        // 拷贝旧数据
        if (size - index >= 0) System.arraycopy(elements, index, elements, index + 1, size - index);

        elements[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        /*
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        rangeCheck(index);

        // 旧元素
        E oldElement = elements[index];
        // 0 1 2 3 4 5 6 7
        // 比如移除索引3,取剩下索引4开始遍历赋值
        for (int i = index + 1; i < size; ++i) {
            // 要从被移除的位置开始赋值
            elements[i - 1] = elements[i];
        }
        // 最后一个元素置空
        elements[--size] = null;
        return oldElement;
    }

    /**
     * 查看元素的索引
     *
     * @param element
     * @return
     */
    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; ++i) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; ++i) {
                if (element.equals(elements[i])) return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 容器自动扩容
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity > capacity) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        // 从旧的赋值过来
        if (size >= 0) System.arraycopy(elements, 0, newElements, 0, size);

        elements = newElements;
        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

    @Override
    public String toString() {
        // size=3, [99, 88, 77]
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(elements[i]);
//			if (i != size - 1) {
//				string.append(", ");
//			}
        }
        string.append("]");
        return string.toString();
    }
}
