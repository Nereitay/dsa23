package es.kiwi.datastructure.stack;
/**
 * 双栈模拟队列
 *
 * <ul>
 *     <li>调用 push、pop 等方法的次数最多 100</li>
 * </ul>
 */
public class E04Leetcode232 {
    ArrayStack<Integer> s1 = new ArrayStack<>(100); // 出栈
    ArrayStack<Integer> s2 = new ArrayStack<>(100); // 进栈
    public E04Leetcode232() {

    }

    public void push(int x) { //向队列尾添加
        s2.push(x);
    }

    public int pop() { // 从对列头移除
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.pop();
    }

    public int peek() { // 从对列头获取
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
