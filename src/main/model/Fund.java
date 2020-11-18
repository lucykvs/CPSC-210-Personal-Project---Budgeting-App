package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.EnumSet;

// Represents a source of income having a brief description and total amount in dollars
public class Fund implements Writable {
    private String description;
    private double amount;
    private Category category;
    public EnumSet<Category> fundCategories;

    // EFFECTS: constructs an income with an associated description and amount
    public Fund(Category category, String description, double amount) {
        this.category = category;
        this.description = description;
        this.amount = amount;
        fundCategories = EnumSet.of(Category.EMPLOYMENT,Category.LOAN, Category.GIFT, Category.OTHER);
    }

    public EnumSet getFundCategories() {
        return fundCategories;
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

    // EFFECTS: returns JSON representation of fund
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("description", description);
        json.put("amount", amount);
        return json;
    }
}
