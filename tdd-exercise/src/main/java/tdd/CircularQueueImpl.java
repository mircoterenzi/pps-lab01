package tdd;

import java.util.ArrayList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {
    private final List<Integer> queue = new ArrayList<>();

    @Override
    public void enqueue(int value) {
        this.queue.addFirst(value);
    }

    @Override
    public int dequeue() {
        return this.queue.removeLast();
    }
}
