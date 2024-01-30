import java.util.ArrayList;

public class Bank {
    private ArrayList<User> users;
    public User createUser(String fullName, String username, String password, String phoneNumber, double initialBalance) {
        // Check if the username is already taken
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists. Please choose a different username.");
                return null;
            }
        }

        User newUser = new User(fullName, username, password, phoneNumber, initialBalance);
        users.add(newUser);
        return newUser;
    }

    public Bank() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByAccountNumber(String accountNumber) {
        for (User user : users) {
            if (user.getAccountNumber().equals(accountNumber)) {
                return user;
            }
        }
        return null;
    }

}
