package es.kiwi.datastructure.priorityqueue;

public class Entry implements Priority{

    String value;
    int priority;

    public Entry(String value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    /**
     * 返回对象的优先级, 约定数字越大, 优先级越高
     *
     * @return 优先级
     */
    @Override
    public int priority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "value='" + value + '\'' +
                ", priority=" + priority +
                '}';
    }
}
