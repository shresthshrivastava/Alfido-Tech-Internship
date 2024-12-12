import java.util.Scanner;
import java.io.*;

class BankAccount {
    private String accountHolderName;
    private double balance;
    private String accountNumber;

    // Constructor to create an account with an initial balance
    public BankAccount(String accountHolderName, double initialBalance, String accountNumber) {
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.accountNumber = accountNumber;
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Current balance: " + balance);
        } else {
            System.out.println("Amount must be greater than zero.");
        }
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Current balance: " + balance);
        } else if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
        } else {
            System.out.println("Insufficient balance. Current balance: " + balance);
        }
    }

    // Method to check the balance
    public double checkBalance() {
        return balance;
    }

    // Method to save account information to a file
    public void saveAccountInfo() {
        try (FileWriter fileWriter = new FileWriter(accountNumber + ".txt")) {
            fileWriter.write("Account Holder: " + accountHolderName + "\n");
            fileWriter.write("Account Number: " + accountNumber + "\n");
            fileWriter.write("Balance: " + balance + "\n");
        } catch (IOException e) {
            System.out.println("Error saving account information.");
        }
    }

    // Method to display account details
    public void displayAccountDetails() {
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String accountNumber;
        String accountHolderName;
        double initialBalance;

        // Create an account
        System.out.println("Enter account holder name: ");
        accountHolderName = scanner.nextLine();
        System.out.println("Enter initial deposit amount: ");
        initialBalance = scanner.nextDouble();
        System.out.println("Enter account number: ");
        accountNumber = scanner.next();

        // Create BankAccount object
        BankAccount account = new BankAccount(accountHolderName, initialBalance, accountNumber);

        // Menu for operations
        int choice;
        do {
            System.out.println("\nWelcome to the Bank!");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. View Account Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    account.saveAccountInfo();
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    account.saveAccountInfo();
                    break;

                case 3:
                    System.out.println("Current balance: " + account.checkBalance());
                    break;

                case 4:
                    account.displayAccountDetails();
                    break;

                case 5:
                    System.out.println("Thank you for using our banking system!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
