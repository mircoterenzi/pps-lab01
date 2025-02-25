package tdd;

import java.util.ArrayList;
import java.util.List;

public class MinMaxStackImpl implements MinMaxStack {
    private final List<Integer> values = new ArrayList<>();
    private final List<Integer> ordered = new ArrayList<>();

    private void checkStackIsNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("No element in stack");
        }
    }

    @Override
    public void push(int value) {
        if (isEmpty() || value < ordered.getFirst()) {
            ordered.addFirst(value);
        } else if (value > ordered.getLast()) {
            ordered.addLast(value);
        }
        this.values.add(value);
    }

    @Override
    public int pop() {
        checkStackIsNotEmpty();
        Integer value = values.removeLast();
        ordered.remove(value);
        return value;
    }

    @Override
    public int peek() {
        checkStackIsNotEmpty();
        return values.getLast();
    }

    @Override
    public int getMin() {
        checkStackIsNotEmpty();
        return ordered.getFirst();
    }

    @Override
    public int getMax() {
        checkStackIsNotEmpty();
        return ordered.getLast();
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public int size() {
        return values.size();
    }
}
