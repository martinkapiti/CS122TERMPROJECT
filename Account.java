import java.util.ArrayList;

public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected ArrayList<Transaction> transactionHistory = new ArrayList<>();

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Invalid or insufficient funds.");
        }
    }

    public boolean transferTo(Account recipient, double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.withdraw(amount);
            recipient.deposit(amount);
            transactionHistory.add(new Transaction("Transfer to " + recipient.accountNumber, amount));
            return true;
        } else {
            System.out.println("Transfer failed: Insufficient funds or invalid amount.");
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : transactionHistory) {
                System.out.println(t);
            }
        }
    }

    public abstract void displayAccountType();
}
