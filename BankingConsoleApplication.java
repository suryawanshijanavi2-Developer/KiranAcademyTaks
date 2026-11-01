
import java.util.Scanner;

public class BankingConsoleApplication {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double balance = 10000;

        System.out.println("Welcome to Kiran Bank");
        System.out.println("Opening Balance: Rs." + balance);

        while (true) {

            System.out.println("\n===== BANKING MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid choice! Please enter a number.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();

            if (choice == 1) {

                // Check Balance
                System.out.println("Current Balance: Rs." + balance);

            } else if (choice == 2) {

                // Deposit Money
                System.out.print("Enter deposit amount: ");

                if (!sc.hasNextDouble()) {
                    System.out.println("Invalid amount!");
                    sc.next();
                    continue;
                }

                double amount = sc.nextDouble();

                if (amount > 0) {
                    balance = balance + amount;

                    System.out.println("Deposit successful.");
                    System.out.println("Updated Balance: Rs." + balance);

                } else {
                    System.out.println("Invalid amount! Enter amount greater than zero.");
                }

            } else if (choice == 3) {

                // Withdraw Money
                System.out.print("Enter withdrawal amount: ");

                if (!sc.hasNextDouble()) {
                    System.out.println("Invalid amount!");
                    sc.next();
                    continue;
                }

                double amount = sc.nextDouble();

                if (amount <= 0) {
                    System.out.println("Invalid amount! Enter amount greater than zero.");

                } else if (amount <= balance) {

                    balance = balance - amount;

                    System.out.println("Withdrawal successful.");
                    System.out.println("Updated Balance: Rs." + balance);

                } else {

                    System.out.println("Transaction failed: Insufficient balance.");
                    System.out.println("Current Balance: Rs." + balance);
                }

            } else if (choice == 4) {

                // Exit
                System.out.println("Thank you for banking with us. Goodbye!");
                break;

            } else {

                System.out.println("Invalid choice! Please select 1 to 4.");
            }
        }

        sc.close();
    }
}
