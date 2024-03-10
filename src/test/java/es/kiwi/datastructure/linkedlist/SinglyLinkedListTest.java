package es.kiwi.datastructure.linkedlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    @Test
    void addFirstTest() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        list.loop();
    }

    @Test
    void loop1Test() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        list.loop1(value -> System.out.println(value));
    }

    @Test
    void loop2Test() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        list.loop2(value -> System.out.println(value));
    }


    @Test
    void iteratorTest() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        for (Integer value : list) {
            System.out.println(value);
        }
    }

    @Test
    void addLastTest() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        Assertions.assertIterableEquals(List.of(1,2,3,4), list);
    }

    @Test
    void test() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.test();
    }

    @Test
    void getTest() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        int i = list.get(2);
        System.out.println(i);

        assertThrows(IllegalArgumentException.class, () -> list.get(10));
    }

    @Test
    void insertTest() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.insert(4, 5);
        list.insert(0, 0);
        list.loop();

        assertThrows(IllegalArgumentException.class, () -> list.insert(10, 5));
    }

    @Test
    void removeFirstTest() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.loop();
        System.out.println("===========================");
        list.removeFirst();
        list.loop();
        System.out.println("===========================");
        list.removeFirst();
        list.loop();
        System.out.println("===========================");
        list.removeFirst();
        list.loop();
        System.out.println("===========================");
        list.removeFirst();
        list.loop();
        System.out.println("===========================");
        assertThrows(IllegalArgumentException.class, list::removeFirst);

    }

    @Test
    void removeTest() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.loop();
        System.out.println("===========================");
        list.remove(2);
        list.loop();
        System.out.println("===========================");
        list.remove(0);
        list.loop();
        System.out.println("===========================");
        assertThrows(IllegalArgumentException.class, () -> list.remove(2));
    }

    @Test
    void loop3Test() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.loop3();

        System.out.println("=========================");

        list.loop3(value -> System.out.println("before: " + value),
                value -> System.out.println("after: " + value));
    }
}