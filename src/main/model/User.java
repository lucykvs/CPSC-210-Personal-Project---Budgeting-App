package model;

// Represents a user with a username and budget. This class is mainly for future use.
public class User {
    public String name;                   //username of account user
    public Budget budget;                 //budget of account user

    // EFFECTS: Constructs a user with given username and an empty budget
    public User(String username) {
        budget = new Budget();
        name = username;
    }

    // EFFECTS: returns name of user
    public String getName() {
        return name;
    }

    // EFFECTS: returns budget of user
    public Budget getBudget() {
        return budget;
    }
}
