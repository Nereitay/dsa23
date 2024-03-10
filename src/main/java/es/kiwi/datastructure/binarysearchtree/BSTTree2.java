package es.kiwi.datastructure.binarysearchtree;

import java.util.List;

/**
 * 二叉搜索树, 泛型 key 版本
 */
public class BSTTree2<K extends Comparable<K>, V> {

    static class BSTNode<K, V> {
        K key;
        V value;
        BSTNode<K, V> left;
        BSTNode<K, V> right;

        public BSTNode(K key) {
            this.key = key;
        }

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }

    BSTNode<K, V> root; // 根节点

    /**
     * <h3>查找关键字对应的值</h3>
     *
     * @param key 关键字
     * @return 关键字对应的值
     */
    public V get(K key) {
//        return doGet(root, key); // 对外隐藏 Node

        BSTNode<K, V> node = root;
        while (node != null) {
            int result = key.compareTo(node.key);
            if (result < 0) {
                node = node.left;
            } else if (result > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }

        return null;
    }

    private V doGet(BSTNode<K, V> node, K key) {
        if (node == null) return null;// 没找到
        /*如果是尾递归 要考虑转换成非递归的方式*/
        int result = key.compareTo(node.key);
        if (result < 0) {
            return doGet(node.left, key); // 向左找
        } else if (result > 0) {
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
    public V min() {
        return null;
    }

    /**
     * <h3>查找最大关键字对应值</h3>
     *
     * @return 关键字对应的值
     */
    public V max() {
        return null;
    }

    /**
     * <h3>存储关键字和对应值</h3>
     *
     * @param key   关键字
     * @param value 值
     */
    public void put(K key, V value) {
    }

    /**
     * <h3>查找关键字的前任值</h3>
     *
     * @param key 关键字
     * @return 前任值
     */
    public V predecessor(K key) {
        return null;
    }

    /**
     * <h3>查找关键字的后任值</h3>
     *
     * @param key 关键字
     * @return 后任值
     */
    public V successor(K key) {
        return null;
    }

    /**
     * <h3>根据关键字删除</h3>
     *
     * @param key 关键字
     * @return 被删除关键字对应值
     */
    public V remove(K key) {
        return null;
    }

    // 找 < key 的所有 value
    public List<V> less(K key) {
        return null;
    }

    // 找 > key 的所有 value
    public List<V> greater(K key) {
        return null;
    }

    // 找 >= key1 且 <= key2 的所有值
    public List<V> between(K key1, K key2) {
        return null;
    }
}
