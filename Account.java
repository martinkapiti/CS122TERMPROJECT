import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions;  // Track all transactions

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction("Deposit", amount));
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("Withdraw", amount));
        }
    }

    public void transfer(double amount, Account recipient) {
        if (amount > 0 && balance >= amount) {
            this.withdraw(amount);
            recipient.deposit(amount);
            transactions.add(new Transaction("Transfer to " + recipient.getAccountNumber(), amount));
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
