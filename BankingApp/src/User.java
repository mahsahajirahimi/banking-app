import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String fullName;
    private String username;
    private String password;
    private String phoneNumber;
    private String accountNumber;
    private double accountBalance;

    public User(String fullName, String username, String password, String phoneNumber, double accountBalance) {
        this.fullName = fullName;
        this.username = username;
        this.password = validatePassword(password);
        this.phoneNumber = phoneNumber;
        this.accountNumber = generateAccountNumber();
        this.accountBalance = 100.0;
    }

    private String validatePassword(String password) {
        Scanner scanner = new Scanner(System.in);
        while (!isValidPassword(password)) {
            System.out.println("Invalid password. Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one digit.");
            System.out.println("Please enter a new password:");
            password = scanner.nextLine();
        }
        return password;
    }
    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void transferMoney(User recipient, double amount) {
        if (this.accountBalance >= amount) {
            this.accountBalance -= amount;
            recipient.setAccountBalance(recipient.getAccountBalance() + amount);
            System.out.println("Transfer successful. Amount of " + amount + " was transferred from your account. Your current balance is " + this.accountBalance);
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }

    public void payBill(String billCode, double amount) {
        if (this.accountBalance >= amount) {
            this.accountBalance -= amount;
            System.out.println("Bill payment successful. Amount of " + amount + " was paid from your account. Your current balance is " + this.accountBalance);
        } else {
            System.out.println("Insufficient funds for bill payment.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.accountBalance += amount;
            System.out.println("Deposit successful. Amount of " + amount + " was added to your account. Your current balance is " + this.accountBalance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && this.accountBalance >= amount) {
            this.accountBalance -= amount;
            System.out.println("Withdrawal successful. Amount of " + amount + " was withdrawn from your account. Your current balance is " + this.accountBalance);
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }
}

