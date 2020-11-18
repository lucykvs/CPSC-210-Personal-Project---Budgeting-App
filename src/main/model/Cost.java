package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.EnumSet;

// Represents a cost having a brief description and total amount in dollars
public class Cost implements Writable {
    private String description;
    private double amount;
    private Category category;
    public EnumSet<Category> costCategories;

    // EFFECTS: constructs a cost with an associated category, description, and amount
    public Cost(Category category, String description, double amount) {
        this.category = category;
        this.description = description;
        this.amount = amount;
        costCategories = EnumSet.of(Category.BILLS,Category.DEBT_REPAYMENTS, Category.ONE_TIME_EXPENSES,
                Category.MISCELLANEOUS_PURCHASES, Category.FOR_FUN);
    }

    // EFFECTS: returns category of this income source
    public Category getCategory() {
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

    public EnumSet getCostCategories() {
        return costCategories;
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
