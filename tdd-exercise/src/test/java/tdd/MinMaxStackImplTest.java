package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private static final int[] VALUES = {5, 2, 3, 8, 10, 1};
    private static final int EMPTY_STACK_SIZE = 0;
    private static final int SINGLE_ELEMENT_STACK_SIZE = 1;
    private static final int POPULATED_STACK_SIZE = VALUES.length;
    private static final int MIN_VALUE = Arrays.stream(VALUES).min().orElseThrow();
    private static final int MAX_VALUE = Arrays.stream(VALUES).max().orElseThrow();

    private MinMaxStack stack;
    
    private void populateWithValues(MinMaxStack stack) {
        for (int value : VALUES) {
            stack.push(value);
        }
    }
    
    @BeforeEach
    void setUp() {
        this.stack = new MinMaxStackImpl();
    }

    @Test
    public void testIsInitiallyEmpty() {
        assertAll(
                () -> assertTrue(this.stack.isEmpty()),
                () -> assertEquals(EMPTY_STACK_SIZE, this.stack.size())
        );
    }

    @Test
    public void testPopWithEmptyStack() {
        assertThrows(IllegalStateException.class, this.stack::pop);
    }

    @Test
    public void testPeekWithEmptyStack() {
        assertThrows(IllegalStateException.class, this.stack::peek);
    }

    @Test
    public void testMinWithEmptyStack() {
        assertThrows(IllegalStateException.class, this.stack::getMin);
    }

    @Test
    public void testMaxWithEmptyStack() {
        assertThrows(IllegalStateException.class, this.stack::getMax);
    }

    @Test
    public void testPush() {
        this.stack.push(VALUES[0]);
        assertAll(
                () -> assertFalse(this.stack.isEmpty()),
                () -> assertEquals(SINGLE_ELEMENT_STACK_SIZE, this.stack.size())
        );
    }

    @Test
    public void testPeek() {
        this.stack.push(VALUES[0]);
        assertAll(
                () -> assertEquals(VALUES[0], this.stack.peek()),
                () -> assertEquals(SINGLE_ELEMENT_STACK_SIZE, this.stack.size()),
                () -> assertFalse(this.stack.isEmpty())
        );
    }

    @Test
    public void testPop() {
        this.stack.push(VALUES[0]);
        assertAll(
                () -> assertEquals(VALUES[0], this.stack.pop()),
                () -> assertEquals(EMPTY_STACK_SIZE, this.stack.size()),
                () -> assertTrue(this.stack.isEmpty())
        );
    }

    @Test
    public void testMinMax() {
        this.stack.push(VALUES[0]);
        assertAll(
                () -> assertEquals(VALUES[0], this.stack.getMin()),
                () -> assertEquals(VALUES[0], this.stack.getMax())
        );
    }

    @Test
    public void testPopSequenceWithMultipleElements() {
        populateWithValues(this.stack);
        for (int i = POPULATED_STACK_SIZE - 1; i > 0; i--) {
            assertEquals(VALUES[i], this.stack.pop());
        }
    }

    @Test
    public void testMinMaxMultipleElements() {
        populateWithValues(this.stack);
        assertAll(
                () -> assertEquals(MIN_VALUE, this.stack.getMin()),
                () -> assertEquals(MAX_VALUE, this.stack.getMax())
        );
    }

    @Test
    public void testMinMaxWhenCurrentMinMaxRemoved() {
        this.stack.push(VALUES[0]);
        this.stack.push(MAX_VALUE);
        this.stack.push(MIN_VALUE);
        this.stack.pop();
        this.stack.pop();
        assertAll(
                () -> assertEquals(VALUES[0], this.stack.getMin()),
                () -> assertEquals(VALUES[0], this.stack.getMax())
        );
    }
}