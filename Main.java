import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> customers = new ArrayList<>();
        Customer current = null;

        while (current == null) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 1) {
                System.out.print("Enter your customer ID: ");
                String inputId = sc.nextLine();

                for (Customer c : customers) {
                    if (c.getCustomerId().equals(inputId)) {
                        current = c;
                        break;
                    }
                }

                if (current == null) {
                    System.out.println("Customer not found. Try again or register.");
                }
            } else if (choice == 2) {
                System.out.print("Enter your name: ");
                String name = sc.nextLine();

                System.out.print("Choose account type (1-Checking, 2-Savings): ");
                int accType = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter initial deposit: ");
                double deposit = sc.nextDouble();
                sc.nextLine();

                String customerId = "CUST" + (customers.size() + 1);
                Account account = (accType == 1) ? new CheckingAccount("CHK" + customerId, deposit) : new SavingsAccount("SAV" + customerId, deposit);
                current = new Customer(name, customerId, account);
                customers.add(current);

                System.out.println("Registration successful. Your Customer ID is: " + customerId);
            }
        }

        int choice;
        do {
            System.out.println("\n--- Bank Account Menu ---");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Transactions");
            System.out.println("5. Transfer Funds");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            Account account = current.getAccount();

            switch (choice) {
                case 1:
                    current.displayInfo();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    account.deposit(sc.nextDouble());
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    account.withdraw(sc.nextDouble());
                    break;
                case 4:
                    account.showTransactionHistory();
                    break;
                case 5:
                    sc.nextLine();
                    System.out.print("Enter recipient customer ID: ");
                    String targetId = sc.nextLine();
                    Customer recipient = null;
                    for (Customer c : customers) {
                        if (c.getCustomerId().equals(targetId)) {
                            recipient = c;
                            break;
                        }
                    }
                    if (recipient == null) {
                        System.out.println("Recipient not found.");
                    } else {
                        System.out.print("Enter amount to transfer: ");
                        double amt = sc.nextDouble();
                        boolean success = account.transferTo(recipient.getAccount(), amt);
                        if (success) {
                            System.out.println("Transfer successful.");
                        }
                    }
                    break;
                case 6:
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);

        sc.close();
    }
}
