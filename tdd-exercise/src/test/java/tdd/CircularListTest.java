package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private static final int CAPACITY = 3;
    private CircularQueue queue;

    private void enqueueIntegersUpTo(int n) {
        for (int i = 1; i <= n; i++) {
            this.queue.enqueue(i);
        }
    }

    @BeforeEach
    public void setUp() {
        this.queue = new CircularQueueImpl(CAPACITY);
    }

    @Test
    public void testEnqueueDequeue() {
        enqueueIntegersUpTo(1);
        assertEquals(1, this.queue.dequeue());
    }

    @Test
    public void testEnqueueDequeueWithMultipleValues() {
        enqueueIntegersUpTo(3);
        assertAll(
                () -> assertEquals(1, this.queue.dequeue()),
                () -> assertEquals(2, this.queue.dequeue()),
                () -> assertEquals(3, this.queue.dequeue())
        );
    }

    @Test
    public void testEnqueueMoreThanCapacity() {
        enqueueIntegersUpTo(6);
        assertAll(
                () -> assertEquals(4, this.queue.dequeue()),
                () -> assertEquals(5, this.queue.dequeue()),
                () -> assertEquals(6, this.queue.dequeue())
        );
    }

    @Test
    public void testDequeueEmptyList() {
        assertThrows(IllegalStateException.class, () -> this.queue.dequeue());
    }
}
