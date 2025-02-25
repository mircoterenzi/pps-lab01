package tdd;

/**
 *  Task 3 - TDD for Circular Queue
 *  A simple CircularQueue that stores integers with a **fixed** capacity.
 *  When full, new elements overwrite the oldest ones.
 *  
 *  When removing elements, the oldest ones are removed first.
 *  Therefore, giving [4, 5, 3], the first element to be removed is 4, then 5, and finally 3.
 *  
 *  For the exercise: 
 *   - Think about the test cases you need to write.
 *   - Introduce methods in the interface in order to make the tests pass.
 *   - Refactor
 */
public interface CircularQueue {

    /**
     * Represents the capacity of the queue in number of elements.
     * Once it's full new elements are added overwriting the oldest one.
     */
    int CAPACITY = 3;

    /**
     * Add an integer into the queue.
     * @param value The integer to be added.
     */
    void enqueue(int value);

    /**
     * Removes and return the last element of the queue.
     * @return The last element of the queue.
     */
    int dequeue();
}