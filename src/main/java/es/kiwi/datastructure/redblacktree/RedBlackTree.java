package es.kiwi.datastructure.redblacktree;

import static es.kiwi.datastructure.redblacktree.RedBlackTree.Color.BLACK;
import static es.kiwi.datastructure.redblacktree.RedBlackTree.Color.RED;
/**
 * <h3>红黑树</h3>
 */
public class RedBlackTree {

    enum Color {
        RED, BLACK;
    }

    Node root;

    static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;// 父节点
        Color color = RED;// 颜色

        public Node(int key) {
            this.key = key;
        }

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Node(int key, Color color) {
            this.key = key;
            this.color = color;
        }

        public Node(int key, Color color, Node left, Node right) {
            this.key = key;
            this.color = color;
            this.left = left;
            this.right = right;

            if (left != null) left.parent = this;
            if (right != null) right.parent = this;
        }

        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        Node uncle() {
            if (parent == null || parent.parent == null) return null;

            if (parent.isLeftChild()) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }

        Node sibling() {
            if (parent == null) return null;

            if (this.isLeftChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }
    }

    boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    boolean isBlack(Node node) {
//        return !isRed(node);
        return node == null || node.color == BLACK;
    }

    // 右旋 1. parent 的处理 2. 旋转后新根的父子关系(旋转方法内建立)
    private void rightRotate(Node pink) {
        Node parent = pink.parent;
        Node yellow = pink.left;
        Node green = yellow.right;

        if (green != null) {
            green.parent = pink;
        }
        yellow.right = pink;
        yellow.parent = parent;
        pink.left = green;
        pink.parent = yellow;

        if (parent == null) {
            root = yellow;
        } else if (parent.left == pink) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }

    }

    private void leftRotate(Node pink) {
        Node parent = pink.parent;
        Node yellow = pink.right;
        Node green = yellow.left;

        if (green != null) {
            green.parent = pink;
        }
        yellow.left = pink;
        yellow.parent = parent;
        pink.right = green;
        pink.parent = yellow;
        if (parent == null) {
            root = yellow;
        } else if (parent.left == pink) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }
    }

    /**
     * 新增或更新
     * <br>
     * 正常增、遇到红红不平衡进行调整
     *
     * @param key   键
     * @param value 值
     */
    public void put(int key, Object value) {
        Node p = root;
        Node parent = null;
        // 1. 找空位
        while (p != null) {
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                p.value = value; // 更新
                return;
            }
        }

        // 2. 正常增
        Node inserted = new Node(key, value);
        if (parent == null) {
            root = inserted;
        } else if (key < parent.key) {
            parent.left = inserted;
            inserted.parent = parent;
        } else {
            parent.right = inserted;
            inserted.parent = parent;
        }

        // 3. 遇到红红不平衡进行调整
        fixRedRed(inserted);
    }

    /**
     * 解决红色节点相邻的情况
     *
     * @param x
     */
    void fixRedRed(Node x) {
        // case 1 插入节点是根节点，变黑即可
        if (x == root) {
            x.color = BLACK;
            return;
        }
        // case 2 插入节点父亲是黑色，无需调整
        if (isBlack(x.parent)) return;

        /* case 3 当红红相邻，叔叔为红时
            需要将父亲、叔叔变黑、祖父变红，然后对祖父做递归处理
        */
        Node parent = x.parent;
        Node uncle = x.uncle();
        Node grandpa = parent.parent;
        if (isRed(uncle)) {
            // 此处父变红，左，右子树变黑，=> 保证 从根到任意一个叶子节点，路径中的黑色节点数一样
            parent.color = BLACK;
            uncle.color = BLACK;
            grandpa.color = RED;

            fixRedRed(grandpa);
            return;
        }

        // case 4 当红红相邻，叔叔为黑时
        if (parent.isLeftChild() && x.isLeftChild()) {// LL
            parent.color = BLACK;
            grandpa.color = RED;
            rightRotate(grandpa);
        } else if (parent.isLeftChild() && !x.isLeftChild()) { //LR
            // 先变成LL
            leftRotate(parent);

            x.color = BLACK;
            grandpa.color = RED;
            rightRotate(grandpa);
        } else if (!parent.isLeftChild() && !x.isLeftChild()) { // RR
            parent.color = BLACK;
            grandpa.color = RED;
            leftRotate(grandpa);
        } else { // RL
            rightRotate(parent);
            x.color = BLACK;
            grandpa.color = RED;
            leftRotate(grandpa);
        }

    }

    /**
     * 删除
     * <br>
     * 正常删、会用到李代桃僵技巧、遇到黑黑不平衡进行调整
     * 删黑色节点会失衡
     *
     * @param key 键
     */
    public void remove(int key) {
        // 1. 先找到待删除节点
        Node deleted = find(key);
        if (deleted == null) return;
        doRemove(deleted);
    }

    private void doRemove(Node deleted) {
        // 2. 找到后继节点
        Node replaced = findReplaced(deleted);
        Node parent = deleted.parent;
        // 没有孩子
        if (replaced == null) {
            // case 1 删除的是根节点
            if (deleted == root) {
                root = null;
            } else {
                if (isBlack(deleted)) {
                    // 复杂调整
                    fixDoubleBlack(deleted);
                } else {
                    // 红色叶子，无需任何处理
                }

                if (deleted.isLeftChild()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                deleted.parent = null;
            }
            return;
        }

        // 有一个孩子
        if (deleted.left == null || deleted.right == null) {
            // case 1 删除的是根节点
            if (deleted == root) {
                root.key = replaced.key;
                root.value = replaced.value;
                root.left = root.right = null;
            } else {
                if (deleted.isLeftChild()) {
                    parent.left = replaced;
                } else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.left = deleted.right = deleted.parent = null;

                if (isBlack(deleted) && isBlack(replaced)) {
                    // 复杂调整  @TODO 实际不会有这种情况 因为只有一个孩子时 被删除节点是黑色 那么剩余节点只能是红色不会触发双黑
                    fixDoubleBlack(replaced);
                } else {
                    //case 2 删除是黑，剩下是红
                    replaced.color = BLACK;
                }

            }
            return;
        }

        // case 0 有两个孩子 => 有一个孩子 或 没有孩子
        int t = deleted.key;
        deleted.key = replaced.key;
        replaced.key = t;

        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v;

        doRemove(replaced);

    }

    // 处理双黑 (case3、case4、case5)
    private void fixDoubleBlack(Node x) {
        if (x == root) return;

        Node parent = x.parent;
        Node sibling = x.sibling();
        // case 3 兄弟节点是红色(红色节点的特性：一定有两个黑孩子)
        if (isRed(sibling)) {
            if (x.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            // 父亲和兄弟要变色，保证旋转后颜色平衡
            parent.color = RED;
            sibling.color = BLACK;
            fixDoubleBlack(x);
            return;
        }

        if (sibling != null) {
            // case 4 兄弟是黑色, 两个侄子也是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                sibling.color = RED;
                if (isRed(parent)) {
                    parent.color = BLACK;
                } else {
                    fixDoubleBlack(parent);
                }
            } else {
                // case 5 兄弟是黑色, 侄子有红色
                if (sibling.isLeftChild() && isRed(sibling.left)) {// LL
                    rightRotate(parent);
                    sibling.left.color = BLACK;
                    sibling.color = parent.color;
                } else if (sibling.isLeftChild() && isRed(sibling.right)) { //LR
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                } else if (!sibling.isLeftChild() && isRed(sibling.left)) {// RL
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                } else {// RR
                    leftRotate(parent);
                    sibling.right.color = BLACK;
                    sibling.color = parent.color;
                }
                parent.color = BLACK;

            }
        } else {
            // @TODO 实际也不会出现，触发双黑后，兄弟节点不会为 null
            fixDoubleBlack(parent);
        }

    }

    public boolean contains(int key) {
        return find(key) != null;
    }

    /**
     * 查找删除节点
     *
     * @param key
     * @return
     */
    private Node find(int key) {
        Node p = root;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    /**
     * 查找剩余节点(删剩下的)
     *
     * @param deleted
     * @return
     */
    private Node findReplaced(Node deleted) {
        if (deleted.left == null && deleted.right == null) return null;

        if (deleted.left == null) return deleted.right;
        if (deleted.right == null) return deleted.left;

        Node s = deleted.right;
        while (s.left != null) {
            s = s.left;
        }
        return s;
    }
}
