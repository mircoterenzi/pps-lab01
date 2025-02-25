package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private static final int PIN_NOT_SET = -1;
    private static final int PIN_LENGTH = 4;
    private static final int MAX_ATTEMPTS = 3;

    private int pin, failedAttempts;
    private boolean locked, blocked;

    private void initializeVariables() {
        this.blocked = false;
        this.locked = false;
        this.failedAttempts = 0;
        this.pin = PIN_NOT_SET;
    }

    public SmartDoorLockImpl() {
        initializeVariables();
    }

    @Override
    public void setPin(int pin) {
        if (blocked || locked) {
            throw new IllegalStateException("Cannot set pin if system isn't unlocked");
        }
        if (String.valueOf(pin).length() != PIN_LENGTH) {
            throw new IllegalArgumentException("Wrong pin length");
        }
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (blocked) {
            throw new IllegalStateException("Cannot unlock while the system is blocked");
        }
        if (pin == this.pin) {
            this.locked = false;
        } else if (++this.failedAttempts == MAX_ATTEMPTS) {
            this.blocked = true;
        }
    }

    @Override
    public void lock() {
        if (this.pin == PIN_NOT_SET) {
            throw new IllegalStateException("Cannot lock without a pin set");
        }
        this.locked = true;
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public boolean isBlocked() {
        return this.blocked;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return this.failedAttempts;
    }

    @Override
    public void reset() {
        initializeVariables();
    }
}
