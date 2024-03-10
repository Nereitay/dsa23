package es.kiwi.datastructure.linkedlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListSentinelTest {

    @Test
    void addFirstTest() {
        SinglyLinkedListSentinel list = new SinglyLinkedListSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        list.loop();
    }

    @Test
    void loop1Test() {
        SinglyLinkedListSentinel list = new SinglyLinkedListSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        list.loop1(value -> System.out.println(value));
    }

    @Test
    void loop2Test() {
        SinglyLinkedListSentinel list = new SinglyLinkedListSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        list.loop2(value -> System.out.println(value));
    }


    @Test
    void iteratorTest() {
        SinglyLinkedListSentinel list = getLinkedList();

        for (Integer value : list) {
            System.out.println(value);
        }
    }

    @Test
    void addLastTest() {
        SinglyLinkedListSentinel list = getLinkedList();

        Assertions.assertIterableEquals(List.of(1,2,3,4), list);
    }

    private static SinglyLinkedListSentinel getLinkedList() {
        SinglyLinkedListSentinel list = new SinglyLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        return list;
    }

    @Test
    void test() {
        SinglyLinkedListSentinel list = new SinglyLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.test();
    }

    @Test
    void getTest() {
        SinglyLinkedListSentinel list = getLinkedList();

        assertEquals(3, list.get(2));
        assertThrows(IllegalArgumentException.class, () -> list.get(10));
    }

    @Test
    void insertTest() {
        SinglyLinkedListSentinel list = getLinkedList();
        list.insert(0, 5);

        assertIterableEquals(List.of(5, 1, 2, 3, 4), list);

        assertThrows(IllegalArgumentException.class, () -> list.insert(10, 5));
    }

    @Test
    void removeFirstTest() {
        SinglyLinkedListSentinel list = new SinglyLinkedListSentinel();
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
        SinglyLinkedListSentinel list = new SinglyLinkedListSentinel();
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
}