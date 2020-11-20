package model;

import org.json.JSONObject;

import java.util.Objects;

public abstract class Transaction {
    private String description;
    private double amount;
    private Category category;
    private String type;

    // EFFECTS: constructs an income with an associated description and amount
    public Transaction(Category category, String description, double amount) {
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    // EFFECTS: returns category of this income source
    public Category getCategory() {
        return category;
    }

    // EFFECTS: returns description of this income source
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns amount in dollars of this income source
    public double getAmount() {
        return amount;
    }

    protected void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    // EFFECTS: returns JSON representation of cost
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("description", description);
        json.put("amount", amount);
        json.put("type", type);
        return json;
    }

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
