package ch17;

import base.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/15 0015 18:27
 */
@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo {

    /**
     * 树的总节点
     */
    private int size;

    /**
     * 根节点 Node
     */
    private Node<E> root;

    /**
     * 比较器
     */
    private Comparator<E> comparator;


    /**
     * 无参构造
     */
    public BinarySearchTree() {
        this(null);
    }

    /**
     * 有参构造
     *
     * @param comparator 比较器
     */
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }


    /**
     * 节点数
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清除元素
     */
    public void clear() {
        root = null;
        size = 0;
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
     * 移除元素
     *
     * @param element
     */
    public void remove(E element) {
        remove(node(element));
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

//
//    /**
//     * 前序遍历
//     */
//    public void preorderTraversal() {
//        preorderTraversal(root);
//    }
//
//    private void preorderTraversal(Node<E> node) {
//        if (node == null) return;
//        System.out.println(node.element);
//        preorderTraversal(node.left);
//        preorderTraversal(node.right);
//    }
//
//    /**
//     * 中序遍历
//     */
//    public void inorderTraversal() {
//        inorderTraversal(root);
//    }
//
//    private void inorderTraversal(Node<E> node) {
//        if (node == null) return;
//        inorderTraversal(node.left);
//        System.out.println(node.element);
//        inorderTraversal(node.right);
//    }
//
//    /**
//     * 后序遍历
//     */
//    public void postorderTraversal() {
//        postorderTraversal(root);
//    }
//
//    private void postorderTraversal(Node<E> node) {
//        if (node == null) return;
//
//        postorderTraversal(node.left);
//        postorderTraversal(node.right);
//        System.out.println(node.element);
//    }
//
//    /**
//     * 层序遍历
//     */
//    public void levelOrderTraversal() {
//        if (root == null) return;
//        Queue<Node<E>> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            Node<E> node = queue.poll();
//            System.out.println(node.element);
//            if (node.left != null) {
//                queue.offer(node.left);
//            }
//
//            if (node.right != null) {
//                queue.offer(node.right);
//            }
//        }
//    }


    /**
     * 完全二叉树
     * @return
     */
    public boolean isComplete() {
        if (root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        // 标记是否是子节点
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            // 逻辑处理
            if (leaf && !node.isLeaf()) return false;

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) { // node.left == null && node.right != null
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else {// node.right == null
                leaf = true;
            }
        }

        return true;
    }

    /**
     * 计算高度
     * @return
     */
    public int height() {
        if (root == null) return 0;

        // 树的高度
        int height = 0;
        // 存储着每一层的元素数量
        int levelSize = 1;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelSize == 0) { // 意味着即将要访问下一层
                levelSize = queue.size();
                height++;
            }
        }

        return height;
    }
    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * 找到前驱节点
     *
     * @param node
     * @return
     */
    private Node<E> predecessor(Node<E> node) {
        if (node == null) return null;

        // 前驱节点在左子树当中（left.right.right.right....）
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        // node.parent == null
        // node == node.parent.right
        return node.parent;
    }

    /**
     * 找到后继节点
     *
     * @param node
     * @return
     */
    private Node<E> successor(Node<E> node) {
        if (node == null) return null;

        // 前驱节点在左子树当中（right.left.left.left....）
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    /**
     * 前序遍历
     * @param visitor
     */
    public void preorder(Visitor<E> visitor) {
        if (visitor == null) return;
        preorder(root, visitor);
    }
    private void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    /**
     * 中序遍历
     * @param visitor
     */
    public void inorder(Visitor<E> visitor) {
        if (visitor == null) return;
        inorder(root, visitor);
    }

    private void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        inorder(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    /**
     * 后序遍历
     * @param visitor
     */
    public void postorder(Visitor<E> visitor) {
        if (visitor == null) return;
        postorder(root, visitor);
    }

    private void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        postorder(node.left, visitor);
        postorder(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }


    /**
     * 层级排序
     * @param visitor
     */
    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            // 外界控制
            if (visitor.visit(node.element)) return;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
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
     * 抽象类
     * 容器
     *
     * @param <E>
     */
    public static abstract class Visitor<E> {
        /**
         * 是否停止
         */
        boolean stop;

        /**
         * @return 如果返回true，就代表停止遍历
         */
        public abstract boolean visit(E element);
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

    /**
     * 元素 element
     * 左节点 Node
     * 右节点 Node
     * 父节点 Node
     *
     * @param <E>
     */
    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        /**
         * 构造方法
         *
         * @param element 元素
         * @param parent  父节点
         */
        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        /**
         * 判断是否是叶子节点
         *
         * @return
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * 判断是否是两个节点
         *
         * @return
         */
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

    }


    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
//        return myNode.element;
        return myNode.element + "_p(" + parentString + ")";
    }


}
