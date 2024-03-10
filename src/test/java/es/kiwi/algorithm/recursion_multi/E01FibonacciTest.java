package es.kiwi.algorithm.recursion_multi;

import org.junit.jupiter.api.Test;

import static es.kiwi.algorithm.recursion_multi.E01Fibonacci.fibonacci;
import static org.junit.jupiter.api.Assertions.*;

class E01FibonacciTest {

    @Test
    void fibonacciTest() {
        assertEquals(1, fibonacci(2));
        assertEquals(2, fibonacci(3));
        assertEquals(3, fibonacci(4));
        assertEquals(5, fibonacci(5));
        assertEquals(8, fibonacci(6));
        assertEquals(13, fibonacci(7));
        assertEquals(21, fibonacci(8));
        assertEquals(34, fibonacci(9));
        assertEquals(55, fibonacci(10));
        assertEquals(89, fibonacci(11));
        assertEquals(144, fibonacci(12));
        assertEquals(233, fibonacci(13));

    }
}