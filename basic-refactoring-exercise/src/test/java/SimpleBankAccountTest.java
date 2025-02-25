import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    public static final int INITIAL_ACCOUNT_BALANCE = 100;
    public static final int EMPTY_BALANCE = 0;
    public static final int WITHDWRAW_AMOUNT = 70;
    public static final int DEPOSIT_AMOUNT = 50;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void setUpAccountHolderAndBankAccount() {
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, EMPTY_BALANCE);
    }

    private int getWrongUserId() {
        return accountHolder.getId() + 1;
    }

    @Test
    void testInitialBalance() {
        assertEquals(EMPTY_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_ACCOUNT_BALANCE);
        assertEquals(INITIAL_ACCOUNT_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDepositIntoUnexistingAccount() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_ACCOUNT_BALANCE);
        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> bankAccount.deposit(getWrongUserId(), DEPOSIT_AMOUNT)
                ),
                () -> assertEquals(INITIAL_ACCOUNT_BALANCE, bankAccount.getBalance())
        );
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_ACCOUNT_BALANCE);
        bankAccount.withdraw(accountHolder.getId(), WITHDWRAW_AMOUNT);
        assertEquals(
                INITIAL_ACCOUNT_BALANCE - WITHDWRAW_AMOUNT - SimpleBankAccount.WITHDRAWAL_FEE,
                bankAccount.getBalance()
        );
    }

    @Test
    void testWithdrawFromUnexistingAccount() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_ACCOUNT_BALANCE);
        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> bankAccount.withdraw(getWrongUserId(), WITHDWRAW_AMOUNT)
                ),
                () -> assertEquals(INITIAL_ACCOUNT_BALANCE, bankAccount.getBalance())
        );
    }

    @Test
    void testWithdrawWithoutSufficientFunds() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_ACCOUNT_BALANCE);
        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> bankAccount.withdraw(accountHolder.getId(), INITIAL_ACCOUNT_BALANCE + 1)
                ),
                () -> assertEquals(INITIAL_ACCOUNT_BALANCE, bankAccount.getBalance())
        );
    }

    @Test
    void testWithdrawEntireBalance() {
        assertThrows(
                IllegalArgumentException.class,
                () -> bankAccount.withdraw(accountHolder.getId(), INITIAL_ACCOUNT_BALANCE)
        );
    }
}
