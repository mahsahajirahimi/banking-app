import java.util.Scanner;
public class BankingApp {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        boolean loggedIn = false;

        while (true) {
            System.out.println("Do you already have an account? (yes/no):");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("yes")) {
                // Login
                System.out.println("Enter your username:");
                String username = scanner.nextLine();
                System.out.println("Enter your password:");
                String password = scanner.nextLine();

                User loggedInUser = bank.getUser(username, password);
                if (loggedInUser != null) {
                    System.out.println("WELCOME!\nLogged in as: " + loggedInUser.getUsername());
                    performTransactions(loggedInUser, scanner, bank);
                    loggedIn = false;  // Logout
                } else {
                    System.out.println("Invalid username or password.");
                }
            } else if (response.equalsIgnoreCase("no")) {
                // Sign up
                System.out.println("Enter full name:");
                String fullName = scanner.nextLine();
                System.out.println("Enter phone number:");
                String phoneNumber = scanner.nextLine();
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                while (bank.getUserByUsername(username) != null) {
                    System.out.println("Username already exists. Please enter a different username:");
                    username = scanner.nextLine();
                }
                System.out.println("Enter password:");
                String password = scanner.nextLine();

                double initialBalance = 100.0;
                User newUser = new User(fullName, username, password, phoneNumber, initialBalance);
                bank.addUser(newUser);
                System.out.println("Account created successfully. Please log in to continue.");
            } else {
                System.out.println("Invalid response. Please enter 'yes' or 'no'.");
            }
        }
    }
    public static void performTransactions(User user, Scanner scanner, Bank bank) {
        String choice;
        while (true) {
            System.out.println("\nMenu of Commands:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer money to another account");
            System.out.println("4. Pay bills");
            System.out.println("5. Show account details");
            System.out.print("\nEnter your choice: ");
            choice = scanner.nextLine();

            if (choice.equals("1")) {
                // Deposit
                System.out.print("Enter deposit amount: ");
                double depositAmount = Double.parseDouble(scanner.nextLine());
                user.deposit(depositAmount);
                Transaction depositTransaction = new Transaction("Deposit", depositAmount);
                depositTransaction.printTransactionDetails();
            } else if (choice.equals("2")) {
                // Withdraw
                System.out.print("Enter withdrawal amount: ");
                double withdrawalAmount = Double.parseDouble(scanner.nextLine());
                user.withdraw(withdrawalAmount);
                Transaction withdrawalTransaction = new Transaction("Withdrawal", withdrawalAmount);
                withdrawalTransaction.printTransactionDetails();
            } else if (choice.equals("3")) {
                // Transfer money
                System.out.print("Enter recipient's account number: ");
                String recipientAccountNumber = scanner.nextLine();
                User recipient = bank.getUserByAccountNumber(recipientAccountNumber);
                if (recipient != null) {
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = Double.parseDouble(scanner.nextLine());
                    user.transferMoney(recipient, transferAmount);
                    Transaction transferTransaction = new Transaction("Transfer Money", transferAmount);
                    transferTransaction.printTransactionDetails();
                } else {
                    System.out.println("Recipient not found.");
                }
            } else if (choice.equals("4")) {
                // Pay bills
                System.out.print("Enter bill code: ");
                String billCode = scanner.nextLine();
                System.out.print("Enter bill amount: ");
                double billAmount = Double.parseDouble(scanner.nextLine());
                user.payBill(billCode, billAmount);
                Transaction payBillsTransaction = new Transaction("Pay Bills", billAmount);
                payBillsTransaction.printTransactionDetails();
            } else if (choice.equals("5")) {
                // Show account details
                System.out.println("Full Name: " + user.getFullName());
                System.out.println("Phone Number: " + user.getPhoneNumber());
                System.out.println("Account Number: " + user.getAccountNumber());
                System.out.println("Account Balance: " + user.getAccountBalance());
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }

            System.out.println("\nDo you want to continue? (yes/no):");
            String continueChoice = scanner.nextLine();
            if (continueChoice.equalsIgnoreCase("no")) {
                System.out.println("Logging out...");
                return;
            }
        }
    }
}
