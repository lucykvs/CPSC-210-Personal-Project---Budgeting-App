package model;

import org.json.JSONObject;

import java.util.Objects;

// Represents a transaction having a category, description, and amount
public abstract class Transaction {
    private String description;
    private double amount;
    private Category category;
    private String type;

    // EFFECTS: constructs a transaction with an associated category, description, and amount
    public Transaction(Category category, String description, double amount) {
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    // EFFECTS: returns category of this transaction
    public Category getCategory() {
        return category;
    }

    // EFFECTS: returns description of this transaction
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns amount in dollars of this transaction
    public double getAmount() {
        return amount;
    }

    // EFFECTS: sets type of this transaction
    protected void setType(String type) {
        this.type = type;
    }

    // EFFECTS: returns type of this transaction
    public String getType() {
        return type;
    }

    // EFFECTS: returns JSON representation of transaction
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("description", description);
        json.put("amount", amount);
        json.put("type", type);
        return json;
    }

    // Override hashcode and equals for desired equality of transactions when GUI's remove functionality is used
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0
                && description.equals(that.description)
                && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, amount, category);
    }
}
