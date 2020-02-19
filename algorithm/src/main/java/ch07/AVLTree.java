package ch07;

import base.tree.BST;

import java.util.Comparator;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/2/19 9:36
 */
public class AVLTree<E> extends BST<E> {
    /**
     * 无参构造
     */
    public AVLTree() {
        this(null);
    }

    /**
     * 比较器构造
     *
     * @param comparator
     */
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }


    /**
     * 添加节点之后的操作
     * @param node 新添加的节点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
                // 整棵树恢复平衡
                break;
            }
        }
    }


    /**
     * 移除节点之后的操作
     * @param node 被删除的节点
     */
    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
            }
        }
    }

    /**
     * 创建节点
     *
     * @param element
     * @param parent
     * @return
     */
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }


    /**
     * 恢复平衡
     *
     * @param grand
     */
    private void rebalance2(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                // 向右转一次
                rotateRight(grand);
            } else { // LR
                // 先转左再转右
                rotateLeft(parent);
                rotateRight(grand);
            }

        } else { // R
            if (node.isLeftChild()) { // RL
                // 先转右再转左
                rotateRight(parent);
                rotateLeft(grand);
            } else { // RR
                // 向左转一次
                rotateLeft(grand);
            }
        }

    }

    /**
     * 恢复平衡
     *
     * @param grand
     */
    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotate(grand, node, node.right, parent, parent.right, grand);
            } else { // LR
                rotate(grand, parent, node.left, node, node.right, grand);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotate(grand, grand, node.left, node, node.right, parent);
            } else { // RR
                rotate(grand, grand, parent.left, parent, node.left, node);
            }
        }
    }

    /**
     * 节点旋转
     * @param r
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     */
    private void rotate(
            Node<E> r, // 子树的根节点
            Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f) {
        // 让d成为这棵子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        //b-c
        b.right = c;
        if (c != null) {
            c.parent = b;
        }
        updateHeight(b);

        // e-f
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        updateHeight(f);

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        updateHeight(d);
    }

    /**
     * 旋转左边
     *
     * @param grand
     */
    private void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 旋转右边
     *
     * @param grand
     */
    private void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 交换完旋转
     *
     * @param grand
     * @param parent
     * @param child
     */
    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 让parent称为子树的根节点
        parent.parent = grand.parent; // 节点对接上
        if (grand.isLeftChild()) { // 判断是否是左节点
            grand.parent.left = parent;
        } else if (grand.isRightChild()) { // 判断是否是右节点
            grand.parent.right = parent;
        } else { // grand是root节点
            root = parent;
        }
        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;

        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     * 判断是否平衡
     *
     * @param node
     * @return
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    /**
     * 更新高度
     *
     * @param node
     */
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    /**
     * avl Node
     *
     * @param <E>
     */
    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        /**
         * 构造方法
         *
         * @param element 元素
         * @param parent  父节点
         */
        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 左右平衡点
         *
         * @return
         */
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }


        /**
         * 更新高度
         */
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;

            height = 1 + Math.max(leftHeight, rightHeight);
        }

        /**
         * 找到更高的子节点
         *
         * @return
         */
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;

            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;

            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }
}
