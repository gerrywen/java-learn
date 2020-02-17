package base.tree;

import java.util.Comparator;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/2/17 17:08
 */
@SuppressWarnings("unchecked")
public class BST<E> extends BinaryTree {
    /**
     * 比较器
     */
    private Comparator<E> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 添加元素
     *
     * @param element 新元素
     */
    public void add(E element) {
        elementNotNullCheck(element);
        // 添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        // 添加的不是第一个节点
        // 找到父节点
        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        do {
            cmp = compare(element, parent.element);
            parent = node;
            if (cmp > 0) {
                node = parent.right;
            } else if (cmp < 0) {
                node = parent.left;
            } else {// 相等
                node.element = element;
                return;
            }
        } while (node != null);
        // 看看插入到父节点的哪个位置,大于在右边，小于在左边
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    /**
     * 判断元素是否存在
     *
     * @param element 元素
     * @return
     */
    public boolean contains(E element) {
        return node(element) != null;
    }


    /**
     * 移除元素
     *
     * @param element
     */
    public void remove(E element) {
        remove(node(element));
    }

    /**
     * 删除某个元素
     * @param node
     */
    private void remove(Node<E> node) {
        if (node == null) return;

        size--;
        if (node.hasTwoChildren()) {// 度为2的节点
            // 找到后继节点
            Node<E> s =  successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {// node == node.parent.right
                node.parent.right = replacement;
            }
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;
        } else { // node是叶子节点，但不是根节点
            // 判断是在左边还是右边
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {// node == node.parent.right
                node.parent.right = null;
            }
        }

    }


    /**
     * 根据元素循环查找Node对象
     *
     * @param element
     * @return
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            } else { // 只能cmp < 0
                node = node.left;
            }
        }
        return null;
    }

    /**
     * 元素比较
     *
     * @param e1
     * @param e2
     * @return 返回值等于0，代表e1和e2相等；
     * 返回值大于0，代表e1大于e2；
     * 返回值小于0，代表e1小于e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) return comparator.compare(e1, e2);
        return ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * 检查元素不能为空
     *
     * @param element
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }



}
