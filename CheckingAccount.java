class CheckingAccount extends Account {
    public CheckingAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void displayAccountType() {
        System.out.println("Checking Account");
    }
}