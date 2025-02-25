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

    @BeforeEach
    public void setUp() {
        this.queue = new CircularQueueImpl();
    }

    @Test
    public void testEnqueue() {
        this.queue.enqueue(1);
    }

    @Test
    public void testDequeue() {
        this.queue.enqueue(1);
        assertEquals(1, this.queue.dequeue());
    }

    @Test
    public void testEnqueueDequeueWithMultipleValues() {
        this.queue.enqueue(1);
        this.queue.enqueue(2);
        this.queue.enqueue(3);
        assertAll(
                () -> assertEquals(1, this.queue.dequeue()),
                () -> assertEquals(2, this.queue.dequeue()),
                () -> assertEquals(3, this.queue.dequeue())
        );
    }
}
