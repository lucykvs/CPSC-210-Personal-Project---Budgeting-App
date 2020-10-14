package main.model;

// Represents an income having a brief description and total amount in dollars
public class Income {
    private String description;
    private double amount;

    // EFFECTS: constructs an income with an associated description and amount
    public Income(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    // EFFECTS: returns description of this income
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns amount in dollars of this income
    public double getAmount() {
        return amount;
    }
}
