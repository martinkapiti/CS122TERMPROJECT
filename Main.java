import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> customers = new ArrayList<>();

        Account acc1 = new CheckingAccount("CHK123", 500);
        Customer c1 = new Customer("Martin", "STU001", acc1);
        customers.add(c1);

        Account acc2 = new SavingsAccount("SAV999", 1000);
        Customer c2 = new Customer("Martin", "STU002", acc2);
        customers.add(c2);

        System.out.print("Enter your customer ID: ");
        String inputId = sc.nextLine();
        Customer current = null;

        for (Customer c : customers) {
            if (c.getCustomerId().equals(inputId)) {
                current = c;
                break;
            }
        }

        if (current == null) {
            System.out.println("Customer not found.");
            return;
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
                    double dep = sc.nextDouble();
                    account.deposit(dep);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double with = sc.nextDouble();
                    account.withdraw(with);
                    break;
                case 4:
                    account.showTransactionHistory();
                    break;
                case 5:
                    sc.nextLine(); // clear buffer
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
                        double transferAmount = sc.nextDouble();
                        boolean success = account.transferTo(recipient.getAccount(), transferAmount);
                        if (success) {
                            System.out.println("Transfer successful!");
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
