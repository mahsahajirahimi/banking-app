import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Transaction {
    private int transactionId;
    private String transactionType;
    private double amount;
    private Date dateTime;

    public Transaction(String transactionType, double amount) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionId = generateTransactionId();
        this.dateTime = new Date();
    }

    private int generateTransactionId() {
        Random random = new Random();
        return 1000 + random.nextInt(9000);
    }

    public void printTransactionDetails() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Transaction Type: " + transactionType);
        System.out.println("Amount: " + amount);
        System.out.println("Date and Time: " + dateFormat.format(dateTime));
    }
}
