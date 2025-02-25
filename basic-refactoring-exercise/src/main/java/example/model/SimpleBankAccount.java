package example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount implements BankAccount {

    public static final int WITHDRAWAL_FEE = 1;
    private double balance;
    private final AccountHolder holder;

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }
    @Override
    public AccountHolder getHolder(){
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        checkUser(userID);
        this.balance += amount;
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        double totalAmount = amount + WITHDRAWAL_FEE;
        if (!isWithdrawAllowed(totalAmount)) {
            throw new IllegalArgumentException("Not sufficient founds to withdraw " + amount);
        }
        checkUser(userID);
        this.balance -= totalAmount;
    }

    private boolean isWithdrawAllowed(final double amount){
        return this.balance >= amount;
    }

    private void checkUser(final int id) {
        if (this.holder.getId() != id) {
            throw new IllegalArgumentException("The given id is different from the holder of the account");
        }
    }
}
