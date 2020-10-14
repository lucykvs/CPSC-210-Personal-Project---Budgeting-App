package main.model;

// Represents a source of income having a brief description and total amount in dollars
public class Fund {
    private String description;
    private double amount;

    // EFFECTS: constructs an income with an associated description and amount
    public Fund(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    // EFFECTS: returns description of this income source
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns amount in dollars of this income source
    public double getAmount() {
        return amount;
    }
}
