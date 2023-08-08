import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String bank;
    private double balance;

    public Transaction(String bank, double balance) {
        this.bank = bank;
        this.balance = balance;
    }

    @Override

    public String toString() {
        return bank + ": ₹" + balance;
    }
}

class Account {
    private String userId;
    private String userPin;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 500.0; // Initial balance
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(String userId, String userPin) {
        return this.userId.equals(userId) && this.userPin.equals(userPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double balance) {
        if (balance > 0) {
            balance += balance;
            transactionHistory.add(new Transaction("Deposit", balance));
            System.out.println("Deposit successful. Current balance: ₹" + balance);
        } else {
            System.out.println("Invalid deposit balance.");
        }
    }

    public void withdraw(double balance) {
        if (balance > 0 && balance <= balance) {
            balance -= balance;
            transactionHistory.add(new Transaction("Withdraw", balance));
            System.out.println("Withdrawal successful. Current balance: ₹" + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void transfer(Account targetAccount, double balance) {
        if (balance > 0 && balance <= balance) {
            balance -= balance;
            targetAccount.balance += balance;
            transactionHistory.add(new Transaction("Transfer to " + targetAccount.userId, balance));
            System.out.println("Transfer successful. Current balance: ₹" + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Account userAccount = new Account("jay898", "0101");

        System.out.println(" Your Welcome");

        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter user PIN: ");
        String userPin = scanner.nextLine();

        if (userAccount.authenticate(userId, userPin)) {
            System.out.println("Authentication successfully done. you are Welcome, " + userId + "!");

            boolean continueRunning = true;
            while (continueRunning) {
                System.out.println("\nChoose your selection:");
                System.out.println("1. View Balance");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Transaction History");
                System.out.println("6. Quit");
                System.out.print("GO in   your option no.: ");

                int mychoice = scanner.nextInt();
                double balance;

                switch (mychoice) {
                    case 1:
                        System.out.println("Current balance: ₹" + userAccount.getBalance());
                        break;

                    case 2:
                        System.out.print("Enter withdrawal balance: ");
                        balance = scanner.nextDouble();
                        userAccount.withdraw(balance);
                        break;

                    case 3:
                        System.out.print("Enter deposit balance: ");
                        balance = scanner.nextDouble();
                        userAccount.deposit(balance);
                        break;

                    case 4:
                        System.out.print("Enter transfer balance: ");
                        balance = scanner.nextDouble();
                        System.out.print("Enter recipient's user ID: ");
                        String recipientUserId = scanner.next();

                        // Assuming a single recipient account for simplicity
                        Account recipientAccount = new Account("recipient", "");
                        userAccount.transfer(recipientAccount, balance);
                        break;

                    case 5:
                        userAccount.showTransactionHistory();
                        break;

                    case 6:
                        System.out.println("Thank you for using this ATM.");
                        continueRunning = false;
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Authentication failed. You Exit");
        }

        scanner.close();
    }
}
