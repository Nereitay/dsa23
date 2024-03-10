package es.kiwi.datastructure.binarysearchtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Binary Search Tree 二叉搜索树
 */
@SuppressWarnings("all")
public class BSTTree1 {

    BSTNode root; // 根节点

    static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }

    /**
     * <h3>查找关键字对应的值</h3>
     *
     * @param key 关键字
     * @return 关键字对应的值
     */
    public Object get(int key) {
//        return doGet(root, key); // 对外隐藏 Node

        BSTNode node = root;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (node.key < key) {
                node = node.right;
            } else {
                return node.value;
            }
        }

        return null;
    }

    private Object doGet(BSTNode node, int key) {
        if (node == null) return null;// 没找到
        /*如果是尾递归 要考虑转换成非递归的方式*/
        if (key < node.key) {
            return doGet(node.left, key); // 向左找
        } else if (node.key < key) {
            return doGet(node.right, key); // 向右找
        } else {
            return node.value; // 找到了
        }
    }

    /**
     * <h3>查找最小关键字对应值</h3>
     *
     * @return 关键字对应的值
     */
    public Object min() {
//        return doMin(root);

        return min(root);
    }

    private Object min(BSTNode node) {
        if (node == null) return null;
        BSTNode n = node;
        while (n.left != null) {
            n = n.left;
        }
        return n.value;
    }

    private Object doMin(BSTNode node) {
        if (node == null) return null;

        if (node.left == null) return node.value;

        return doMin(node.left); // 尾递归
    }

    /**
     * <h3>查找最大关键字对应值</h3>
     *
     * @return 关键字对应的值
     */
    public Object max() {
        return max(root);
    }

    private Object max(BSTNode node) {
        if (node == null) return null;
        BSTNode n = node;
        while (n.right != null) {
            n = n.right;
        }
        return n.value;
    }

    /**
     * <h3>存储关键字和对应值</h3>
     *
     * @param key   关键字
     * @param value 值
     */
    public void put(int key, Object value) {
        // 1. key 存在 更新
        BSTNode node = root;
        BSTNode parent = null;
        while (node != null) {
            parent = node;
            if (key < node.key) {
                node = node.left;
            } else if (node.key < key) {
                node = node.right;
            } else {
                // 更新
                node.value = value;
                return;
            }
        }
        // 2. key 不存在 新增
        if (parent == null) {
            root = new BSTNode(key, value);
            return;
        }

        BSTNode newNode = new BSTNode(key, value);
        if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

    }

    /**
     * <h3>查找关键字的前任值</h3>
     *
     * @param key 关键字
     * @return 前任值
     */
    public Object predecessor(int key) {

        BSTNode n = root;
        BSTNode ancestorFromLeft = null;
        while (n != null) {
            if (key < n.key) {
                n = n.left;
            } else if (n.key < key) {
                ancestorFromLeft = n;
                n = n.right;
            } else {
                break;
            }
        }

        if (n == null) return null; // 没找到节点

        // 情况1：节点有左子树，此时前驱节点就是左子树的最大值
        if (n.left != null) {
            return max(n.left);
        }
        // 情况2：节点没有左子树，若离它最近的祖先自从左而来，此祖先即为前驱
        return ancestorFromLeft != null ?
                ancestorFromLeft.value : null;
    }

    /**
     * <h3>查找关键字的后任值</h3>
     *
     * @param key 关键字
     * @return 后任值
     */
    public Object successor(int key) {
        BSTNode n = root;
        BSTNode ancestorFromRight = null;
        while (n != null) {
            if (key < n.key) {
                ancestorFromRight = n;
                n = n.left;
            } else if (n.key < key) {
                n = n.right;
            } else {
                break;
            }
        }

        if (n == null) return null; // 没找到节点

        // 情况1：节点有右子树，此时后任就是右子树的最小值
        if (n.right != null) {
            return min(n.right);
        }
        // 情况2：节点没有右子树，若离它最近的、自右而来的祖先就是后任
        return ancestorFromRight != null ?
                ancestorFromRight.value : null;
    }

    /**
     * <h3>根据关键字删除</h3>
     *
     * @param key 关键字
     * @return 被删除关键字对应值
     */
    public Object remove(int key) {
        /*ArrayList<Object> result = new ArrayList<>(); // 保存被删除节点的值
        root = doRemove(root, key, result);
        return result.isEmpty() ? null : result.get(0);*/

        BSTNode node = root;
        BSTNode parent = null;
        while (node != null) {
            if (key < node.key) {
                parent = node;
                node = node.left;
            } else if (node.key < key) {
                parent = node;
                node = node.right;
            } else {
                break;
            }
        }

        if (node == null) return null;

        // 删除操作
        if (node.left == null) {
            // 情况1 - 只有右孩子 ,将右孩子托孤给Parent
            shift(parent, node, node.right);
        } else if (node.right == null) {
            // 情况2 - 只有左孩子 ,将左孩子托孤给Parent
            shift(parent, node, node.left);
        } else {
            // 情况4 左右孩子都有
            // 4.1 被删除节点找后继
            BSTNode sucessor = node.right;
            BSTNode sParent = node;
            while (sucessor.left != null) {
                sParent = sucessor;
                sucessor = sucessor.left;
            }
            // 4.2 删除和后继不相邻, 处理后继的后事
            if (sParent != node) {
                shift(sParent, sucessor, sucessor.right); // successor已经是右边最小，不可能有左孩子了
                sucessor.right = node.right;
            }
            // 4.3 后继取代被删除节点
            shift(parent, node, sucessor);
            sucessor.left = node.left;
        }
        return node.value;
    }

    /*
              4
             / \
            2   6
           /     \
          1       7

              4
             / \
            2   6 doRemove() return 7
           /     \
          1

              4
             / \
            2   7
           /
          1

     */

    /**
     * 🐻‍❄️......不理解
     *
     * @param node   起点
     * @param key
     * @param result
     * @return 删剩下的孩子(找到) 或 null(没找到)
     */
    private BSTNode doRemove(BSTNode node, int key, ArrayList<Object> result) {
        if (node == null) return null;

        if (key < node.key) {
            node.left = doRemove(node.left, key, result);
            return node;
        }
        if (node.key < key) {
            node.right = doRemove(node.right, key, result);
            return node;
        }
        result.add(node.value);

        // 情况1 - 只有右孩子
        if (node.left == null) {
            return node.right;
        }
        // 情况2 - 只有左孩子
        if (node.right == null) {
            return node.left;
        }
        // 情况3 - 有两个孩子
        BSTNode s = node.right;
        while (s.left != null) {
            s = s.left;
        }
        s.right = doRemove(node.right, s.key, new ArrayList<>());
        s.left = node.left;

        return s;
    }

    /**
     * 托孤方法
     *
     * @param parent  被删除节点的父亲
     * @param deleted 被删除节点
     * @param child   被顶上去的节点
     */
    private void shift(BSTNode parent, BSTNode deleted, BSTNode child) {
        if (parent == null) {
            root = child;
        } else if (deleted == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    /*
        思路：
        对于二叉搜索树而言，中序遍历就能得到升序的结果
    */

    /**
     * 找 < key 的所有 value
     *
     * @param key
     * @return
     */
    public List<Object> less(int key) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode node = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key < key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                node = pop.right;
            }
        }
        return result;
    }

    /**
     * 找 > key 的所有 value
     *
     * @param key
     * @return
     */
    public List<Object> greater(int key) {
        /*ArrayList<Object> result = new ArrayList<>();
        BSTNode node = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key > key) {
                    result.add(pop.value);
                }
                node = pop.right;
            }
        }
        return result;*/

        // 反向中序遍历（降序） Reverse in-order RNL - 右值左
        ArrayList<Object> result = new ArrayList<>();
        BSTNode node = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.right;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key > key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                node = pop.left;
            }
        }
        return result;

    }

    /**
     * 找 >= key1 且 <= key2 的所有值
     *
     * @param key1
     * @param key2
     * @return
     */
    public List<Object> between(int key1, int key2) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode node = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key >= key1 && pop.key <= key2) {
                    result.add(pop.value);
                } else if (pop.key > key2){
                    break;
                }
                node = pop.right;
            }
        }
        return result;
    }
}
