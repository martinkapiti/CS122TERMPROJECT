import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Account account = new CheckingAccount("jfkro484ufj", 500.0);
        Customer customer = new Customer("Martin", "martink", account);

        int choice;
        do {
            System.out.println("\n--- Bank Account Menu ---");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = in.nextInt();
            System.out.println("\n----------------------------\n");

            switch (choice) {
                case 1:
                    customer.displayInfo();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = in.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = in.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the bank simulator.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);

        in.close();
    }
}
