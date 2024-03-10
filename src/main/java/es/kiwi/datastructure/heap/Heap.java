package es.kiwi.datastructure.heap;

import java.util.Arrays;

/**
 * 可以扩容的 heap, max 用于指定是大顶堆还是小顶堆
 */
public class Heap {

    int[] array;
    int size;
    boolean isMax;

    public int size() {
        return size;
    }

    public Heap(int capacity, boolean isMax) {
        this.array = new int[capacity];
        this.isMax = isMax;
    }

    public int peek() {
        return array[0];
    }

    public int poll() {
        int top = array[0];

        swap(0, size - 1);
        size--;
        down(0);

        return top;
    }

    /**
     * 删除指定索引处元素
     *
     * @param index 索引
     * @return 被删除元素
     */
    public int poll(int index) {
        int deleted = array[index];
        up(Integer.MAX_VALUE, index);
        poll();
        return deleted;
    }

    /**
     * 替换堆顶元素
     *
     * @param replaced 新元素
     */
    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    public void offer(int offered) {
        if (size == array.length) {
            grow();
        }
        up(offered, size);
        size++;
    }

    private void up(int offered, int index) {
        int child = index;

        while (child > 0) {
            int parent = (child - 1) / 2;
            boolean cmp = isMax ? offered > array[parent] : offered < array[parent];
            if (cmp) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }

        array[child] = offered;
    }

    public Heap(int[] array, boolean isMax) {
        this.array = array;
        this.size = array.length;
        this.isMax = isMax;
        heapify();
    }

    /**
     * 建堆
     */
    private void heapify() {
        // 如何找到最后这个非叶子节点  size / 2 - 1
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    /**
     * 扩容
     */
    private void grow() {
        int capacity = size + (size >> 1);
        int[] newArray = new int[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    /**
     * @param parent 父节点索引
     */
    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int maxOrMin = parent;

        if (left < size &&
                (isMax ? array[left] > array[maxOrMin] : array[left] < array[maxOrMin])) {
            maxOrMin = left;
        }

        if (right < size &&
                (isMax ? array[right] > array[maxOrMin] : array[right] < array[maxOrMin])) {
            maxOrMin = right;
        }

        if (maxOrMin != parent) {
            swap(maxOrMin, parent);
            down(maxOrMin);
        }
    }

    private void swap(int i, int j) {// 交换两个索引的值
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    /*
              100
           /      \
          10      99
         / \      / \
        5   6    98 97
       /\   /\   /
      1 2  3  4 96

              100
           /      \
          96      99
         / \      / \
        10   6   98 97
       /\   /\
      1 2  3  4
     */
    public static void main(String[] args) {
        Heap heap = new Heap(5, true); //100,10,99,5,6,98,97,1,2,3,4,96
        heap.offer(100);
        heap.offer(10);
        heap.offer(99);
        heap.offer(5);
        heap.offer(6);
        heap.offer(98);
        heap.offer(97);
        heap.offer(1);
        heap.offer(2);
        heap.offer(3);
        heap.offer(4);
        heap.offer(96);
        System.out.println(Arrays.toString(heap.array));
        System.out.println(heap.size);
        System.out.println(heap.poll(3));
        System.out.println(Arrays.toString(heap.array));
        System.out.println(heap.size);
    }
}
