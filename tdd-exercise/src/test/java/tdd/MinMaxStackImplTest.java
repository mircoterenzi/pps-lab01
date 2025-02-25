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
    private static final int MAX_VALUE = Arrays.stream(VALUES).max().orElseThrow();
    private static final int MIN_VALUE = Arrays.stream(VALUES).min().orElseThrow();
    
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
    public void testPopWithEmptyStack() {
        assertThrows(IllegalStateException.class, this.stack::pop);
    }

    @Test
    public void testPeekWithEmptyStack() {
        assertThrows(IllegalStateException.class, this.stack::peek);
    }

    @Test
    public void testIsInitiallyEmpty() {
        assertAll(
                () -> assertTrue(this.stack.isEmpty()),
                () -> assertEquals(EMPTY_STACK_SIZE, this.stack.size())
        );
    }

    @Test
    public void testPushValue() {
        this.stack.push(VALUES[0]);
        assertAll(
                () -> assertFalse(this.stack.isEmpty()),
                () -> assertEquals(SINGLE_ELEMENT_STACK_SIZE, this.stack.size())
        );
    }

    @Test
    public void testPushValues() {
        populateWithValues(this.stack);
        assertAll(
                () -> assertFalse(this.stack.isEmpty()),
                () -> assertEquals(POPULATED_STACK_SIZE, this.stack.size())
        );
    }

    @Test
    public void testPushAndPop() {
        this.stack.push(VALUES[0]);
        assertAll(
                () -> assertEquals(VALUES[0], this.stack.pop()),
                () -> assertTrue(this.stack.isEmpty())
        );
    }

    @Test void testPushAndPopSequence() {
        populateWithValues(this.stack);
        for (int i = POPULATED_STACK_SIZE - 1; i > 0; i--) {
            assertEquals(VALUES[i], this.stack.pop());
        }
    }

    @Test
    public void testPeekDoNotRemoveValue() {
        this.stack.push(VALUES[0]);
        assertAll(
                () -> assertEquals(VALUES[0], this.stack.peek()),
                () -> assertEquals(SINGLE_ELEMENT_STACK_SIZE, this.stack.size()),
                () -> assertFalse(this.stack.isEmpty())
        );
    }

    @Test void testPeekEqualsToPopValue() {
        int peekValue, popValue;
        this.stack.push(VALUES[0]);
        peekValue = this.stack.peek();
        popValue = this.stack.pop();
        assertEquals(popValue, peekValue);
    }

    @Test void testGetMinValue() {
        populateWithValues(this.stack);
        assertEquals(MIN_VALUE, this.stack.getMin());
    }

    @Test void testGetMaxValue() {
        populateWithValues(this.stack);
        assertEquals(MAX_VALUE, this.stack.getMax());
    }
}