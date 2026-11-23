import java.util.Scanner;

public class Main {
    private static final int MAX_PIN_ATTEMPTS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Customer customer = new Customer("Joseph", "20002");
        BankAccount account = new BankAccount(customer, 500.00);

        boolean authenticated = false;
        int attempts = 0;

        while (attempts < MAX_PIN_ATTEMPTS && !authenticated) {
            System.out.print("Enter your PIN: ");
            String enteredPin = scanner.nextLine().trim();
            attempts++;

            if (customer.authenticatePin(enteredPin)) {
                authenticated = true;
                System.out.println("Login successful. Welcome, " + customer.getName() + "!");
            } else {
                int remaining = MAX_PIN_ATTEMPTS - attempts;
                if (remaining > 0) {
                    System.out.println("Incorrect PIN. Attempts remaining: " + remaining);
                } else {
                    System.out.println("Maximum PIN attempts reached. Card locked.");
                }
            }
        }

        if (!authenticated) {
            scanner.close();
            return;
        }

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.printf("Current Balance: %.2f%n", account.getBalance());
                    break;
                case "2":
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = readPositiveDouble(scanner);
                    if (depositAmount >= 0 && account.deposit(depositAmount)) {
                        System.out.printf("Deposit successful. New Balance: %.2f%n", account.getBalance());
                    }
                    break;
                case "3":
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = readPositiveDouble(scanner);
                    if (withdrawAmount >= 0 && account.withdraw(withdrawAmount)) {
                        System.out.printf("Withdrawal successful. New Balance: %.2f%n", account.getBalance());
                    }
                    break;
                case "4":
                    account.printTransactionHistory();
                    break;
                case "5":
                    running = false;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please select 1 to 5.");
                    break;
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== ATM Main Menu ===");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. View Transaction History");
        System.out.println("5. Exit");
    }

    private static double readPositiveDouble(Scanner scanner) {
        String input = scanner.nextLine().trim();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid numeric amount.");
            return -1;
        }
    }
}