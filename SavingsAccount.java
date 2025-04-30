public class SavingsAccount extends Account {
    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void displayAccountType() {
        System.out.println("Savings Account");
    }
}
