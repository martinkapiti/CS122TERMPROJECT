public class Customer {
    private String name;
    private String customerId;
    private Account account;

    public Customer(String name, String customerId, Account account) {
        this.name = name;
        this.customerId = customerId;
        this.account = account;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Account getAccount() {
        return account;
    }

    public void displayInfo() {
        System.out.println("Customer: " + name);
        System.out.println("ID: " + customerId);
        account.displayAccountType();
        System.out.println("Balance: $" + account.getBalance());
    }
}
