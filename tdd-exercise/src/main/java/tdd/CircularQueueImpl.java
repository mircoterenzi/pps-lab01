package tdd;

import java.util.ArrayList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {
    private final int capacity;
    private final List<Integer> queue = new ArrayList<>();

    /**
     * The queue has a fixed capacity expressed in number of elements.
     * Once it's full new elements are added overwriting the oldest one.
     * @param capacity The capacity to be set for the queue.
     */
    CircularQueueImpl(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void enqueue(int value) {
        if (queue.size() > this.capacity - 1) {
            queue.removeLast();
        }
        this.queue.addFirst(value);
    }

    @Override
    public int dequeue() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("The queue is empty, no element can be dequeue");
        }
        return this.queue.removeLast();
    }
}
