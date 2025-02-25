package tdd;

import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack {
    private final Stack<Integer> values = new Stack<>();

    @Override
    public void push(int value) {
        this.values.add(value);
    }

    private void checkStackIsNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("No element in stack");
        }
    }

    @Override
    public int pop() {
        checkStackIsNotEmpty();
        return values.pop();
    }

    @Override
    public int peek() {
        checkStackIsNotEmpty();
        return values.peek();
    }

    @Override
    public int getMin() {
        int min = values.getFirst();
        for (int i = 1; i < values.size(); i++) {
            if (min > values.get(i)) {
                min = values.get(i);
            }
        }
        return min;
    }

    @Override
    public int getMax() {
        int max = values.getFirst();
        for (int i = 1; i < values.size(); i++) {
            if (max < values.get(i)) {
                max = values.get(i);
            }
        }
        return max;
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
