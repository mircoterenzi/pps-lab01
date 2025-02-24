package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    public static final int PIN = 1234;
    public static final int WRONGLY_FORMATTED_PIN = 2;

    private SmartDoorLock lock;

    private int getWrongPin() {
        return PIN + 1;
    }

    private void setPinAndLock() {
        this.lock.setPin(PIN);
        this.lock.lock();
    }

    private void tryWrongPinUntilBlock() {
        setPinAndLock();
        for (int i = 0; i < this.lock.getMaxAttempts(); i++) {
            this.lock.unlock(getWrongPin());
        }
    }

    @BeforeEach
    public void createSmartDoorLock() {
        lock = new SimpleSmartDoorLock();
    }

    @Test
    public void testLockWithoutPinSet() {
        assertThrows(
                IllegalStateException.class,
                () -> this.lock.lock()
        );
    }

    @Test
    public void testLock() {
        setPinAndLock();
        assertTrue(this.lock.isLocked());
    }

    @Test void testLockWithPinNotCorrectlyFormatted() {
        assertThrows(IllegalArgumentException.class, () -> this.lock.setPin(WRONGLY_FORMATTED_PIN));
    }

    @Test
    public void testSetPinWhileLocked() {
        setPinAndLock();
        assertThrows(IllegalStateException.class, () -> this.lock.setPin(PIN));
    }

    @Test
    public void testUnlock() {
        setPinAndLock();
        this.lock.unlock(PIN);
        assertFalse(this.lock.isLocked());
    }

    @Test
    public void testUnlockWithWrongPin() {
        setPinAndLock();
        this.lock.unlock(getWrongPin());
        assertAll(
                () -> assertTrue(this.lock.isLocked()),
                () -> assertEquals(1, this.lock.getFailedAttempts())
        );
    }

    @Test
    public void testBlock() {
        tryWrongPinUntilBlock();
        assertTrue(this.lock.isBlocked());
    }

    @Test
    public void testUnlockWhileBlocked() {
        tryWrongPinUntilBlock();
        assertThrows(IllegalStateException.class, () -> this.lock.unlock(PIN));
    }

    @Test
    public void testSetPinWhileBlocked() {
        tryWrongPinUntilBlock();
        assertThrows(IllegalStateException.class, () -> this.lock.setPin(PIN));
    }
    
    @Test
    public void testReset() {
        tryWrongPinUntilBlock();
        this.lock.reset();
        assertAll(
                () -> assertFalse(this.lock.isBlocked()),
                () -> assertFalse(this.lock.isLocked()),
                () -> assertEquals(0, this.lock.getFailedAttempts()),
                () -> assertThrows(IllegalStateException.class, () -> this.lock.lock())
        );
    }
}
