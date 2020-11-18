package model;

import org.json.JSONObject;

public class Transaction {
    private String description;
    private double amount;
    private Category category;

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

    // EFFECTS: returns JSON representation of cost
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("description", description);
        json.put("amount", amount);
        return json;
    }
}
