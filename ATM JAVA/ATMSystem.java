import java.util.ArrayList;
import java.util.Scanner;

class User {
    private int userId;
    private int pin;

    public User(int userId, int pin) {
        this.userId = userId;
        this.pin = pin;
    }

    public int getUserId() {
        return userId;
    }

    public int getPin() {
        return pin;
    }
}

class BankAccount {
    private double balance;
    private ArrayList<String> transactions;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
        transactions = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposit: +" + amount);
            System.out.println("Money Deposited: " + amount);
        } else {
            System.out.println("Invalid amount for deposit!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add("Withdrawal: -" + amount);
            System.out.println("Money Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient funds or invalid amount for withdrawal!");
        }
    }

    public void transfer(double amount, BankAccount recipient) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            transactions.add("Transfer: -" + amount + " to " + recipient.toString());
            System.out.println("Money Transferred: " + amount + " to User ID: " + recipient.toString());
        } else {
            System.out.println("Insufficient funds or invalid amount for transfer!");
        }
    }

    @Override
    public String toString() {
        return "Account-" + hashCode();
    }
}

class ATM {
    private User user;
    private BankAccount account;
    private Scanner scanner;

    public ATM(User user, BankAccount account) {
        this.user = user;5
        this.account = account;
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("******** WELCOME TO ATM ********");
        System.out.println("ATM Menu:");
        System.out.println("1. Deposit Cash");
        System.out.println("2. Withdraw Cash");
        System.out.println("3. Transfer Money");
        System.out.println("4. View Transactions History");
        System.out.println("5. Quit");
    }

    public void start() {
        int choice;
        do {
            showMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    transfer();
                    break;
                case 4:
                    viewTransactionsHistory();
                    break;
                case 5:
                    System.out.println("Exiting ATM. Thank You!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option!");
            }
        } while (choice != 5);
    }

    private void deposit() {
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    private void withdraw() {
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    private void transfer() {
        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter recipient's User ID: ");
        int recipientUserId = scanner.nextInt();

        BankAccount recipientAccount = new BankAccount(0);

        if (recipientUserId == account.getUserId()) {
            System.out.println("Cannot transfer to the same account!");
        } else {
            account.transfer(amount, recipientAccount);
        }
    }

    private void viewTransactionsHistory() {
        ArrayList<String> transactions = account.getTransactions();
        System.out.println("Transactions History:");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        User user = new User(123456, 7890);
        BankAccount bankAccount = new BankAccount(1000.0);
        ATM atm = new ATM(user, bankAccount);
        atm.start();
    }
}