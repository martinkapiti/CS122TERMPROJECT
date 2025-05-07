import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Welcome to the Bank Simulator ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> login();
                case 2 -> register();
                case 3 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void login() {
        System.out.print("Enter your Customer ID: ");
        String id = scanner.nextLine();

        Customer current = null;
        for (Customer c : customers) {
            if (c.getCustomerId().equals(id)) {
                current = c;
                break;
            }
        }

        if (current != null) {
            System.out.println("Welcome back, " + current.getName() + "!");
            accountMenu(current);
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void register() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Choose account type:");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        int accType = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Initial deposit: ");
        double deposit = scanner.nextDouble();
        scanner.nextLine();

        String id = "CUST" + (customers.size() + 1);
        Account account;

        if (accType == 1) {
            account = new CheckingAccount("CHK" + id, deposit);
        } else {
            account = new SavingsAccount("SAV" + id, deposit);
        }

        Customer newCustomer = new Customer(name, id, account);
        customers.add(newCustomer);

        System.out.println("Registration successful. Your Customer ID is: " + id);
    }

    private static void accountMenu(Customer customer) {
        while (true) {
            System.out.println("\n--- Account Menu ---");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Transactions");
            System.out.println("5. Transfer Funds");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> System.out.println("Current Balance: $" + customer.getAccount().getBalance());
                case 2 -> {
                    System.out.print("Enter amount to deposit: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    customer.getAccount().deposit(amount);
                }
                case 3 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    customer.getAccount().withdraw(amount);
                }
                case 4 -> {
                    System.out.println("Transaction History:");
                    for (Transaction t : customer.getAccount().getTransactions()) {
                        System.out.println(t);
                    }
                }
                case 5 -> {
                    System.out.print("Enter recipient Customer ID: ");
                    String recipientId = scanner.nextLine();
                    Customer recipient = null;
                    for (Customer c : customers) {
                        if (c.getCustomerId().equals(recipientId)) {
                            recipient = c;
                            break;
                        }
                    }

                    if (recipient == null) {
                        System.out.println("Recipient not found.");
                        break;
                    }

                    System.out.print("Enter amount to transfer: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    customer.getAccount().transfer(amount, recipient.getAccount());
                }
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
