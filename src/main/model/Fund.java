package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a source of income having a brief description and total amount in dollars
public class Fund implements Writable {
    private String description;
    private double amount;
    private FundCategory category;

    // EFFECTS: constructs an income with an associated description and amount
    public Fund(FundCategory category, String description, double amount) {
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    // EFFECTS: returns category of this income source
    public FundCategory getCategory() {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("description", description);
        json.put("amount", amount);
        return json;
    }
}
