public class BankAccount {
    private static final int MAX_TRANSACTIONS = 100;

    private final Customer owner;
    private double balance;
    private final String[] transactions;
    private int transactionCount;

    public BankAccount(Customer owner, double initialBalance) {
        this.owner = owner;
        this.balance = Math.max(0.0, initialBalance);
        this.transactions = new String[MAX_TRANSACTIONS];
        this.transactionCount = 0;

        if (this.balance > 0.0) {
            recordTransaction(String.format("Initial balance: %.2f", this.balance));
        }
    }

    public Customer getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Deposit amount must be greater than zero.");
            return false;
        }
        balance += amount;
        recordTransaction(String.format("Deposited: +%.2f | Balance: %.2f", amount, balance));
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be greater than zero.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Error: Insufficient balance.");
            return false;
        }
        balance -= amount;
        recordTransaction(String.format("Withdrew: -%.2f | Balance: %.2f", amount, balance));
        return true;
    }

    public void printTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        if (transactionCount == 0) {
            System.out.println("No transactions recorded yet.");
            return;
        }
        for (int i = 0; i < transactionCount; i++) {
            System.out.println((i + 1) + ". " + transactions[i]);
        }
    }

    private void recordTransaction(String entry) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount] = entry;
            transactionCount++;
        } else {
            System.out.println("Notice: Transaction history limit reached; oldest entry retained.");
        }
    }
}