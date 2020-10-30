package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a cost having a brief description and total amount in dollars
public class Cost implements Writable {
    private String description;
    private double amount;
    private CostCategory category;

    // EFFECTS: constructs a cost with an associated category, description, and amount
    public Cost(CostCategory category, String description, double amount) {
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    // EFFECTS: returns category of this income source
    public CostCategory getCategory() {
        return category;
    }

    // EFFECTS: returns description of this cost
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns amount in dollars of this cost
    public double getAmount() {
        return amount;
    }

    // EFFECTS: returns JSON representation of cost
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("description", description);
        json.put("amount", amount);
        return json;
    }
}
