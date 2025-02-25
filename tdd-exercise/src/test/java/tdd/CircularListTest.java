package tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private CircularQueue queue;

    @Test
    public void testEnqueue() {
        CircularQueue queue = new CircularQueueImpl();
        queue.enqueue(1);
    }

    @Test
    public void testDequeue() {
        CircularQueue queue = new CircularQueueImpl();
        queue.enqueue(1);
        assertEquals(1, queue.dequeue());
    }
}
