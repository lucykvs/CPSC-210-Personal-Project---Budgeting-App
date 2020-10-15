package model;

// Represents a user having a username and a budget. This class is mainly for future use.
public class User {
    public String name;                   //username of account user
    public Budget budget;                 //budget of account user

    // Constructs a user with given username and an empty budget
    public User(String username) {
        budget = new Budget();
        name = username;
    }

    public String getName() {
        return name;
    }

    public Budget getBudget() {
        return budget;
    }
}
