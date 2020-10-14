package model;

// Represents a costs having a brief description and total amount in dollars
public class Cost {
    private String description;
    private double amount;

    // EFFECTS: constructs a cost with an associated description and amount
    public Cost(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    // EFFECTS: returns description of this cost
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns amount in dollars of this cost
    public double getAmount() {
        return amount;
    }
}
