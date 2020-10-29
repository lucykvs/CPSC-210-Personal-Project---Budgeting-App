package model;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

// Represents a collection of costs comprising a budget's expenses
public class Expenses {
    private List<Cost> expenses;

    // EFFECTS: constructs an empty collection of costs
    public Expenses() {
        expenses = new ArrayList<>();
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: adds a new cost to the collection of expenses
    public void addCost(CostCategory category, String description, double amount) {
        Cost c = new Cost(category, description, amount);
        expenses.add(c);
    }

    // MODIFIES: this
    // EFFECTS: returns list of the descriptions of those costs in list of expenses;
    // list returned can be empty.
    public List<String> getAllCostDescriptions() {
        List<String> descriptions = new ArrayList<>();

        for (Cost c : expenses) {
            descriptions.add(c.getDescription());
        }
        return descriptions;
    }

    // MODIFIES: this
    // EFFECTS: returns total amount in dollars of all cost amounts in list of expenses
    public double getTotalExpenses() {
        double total = 0;
        for (Cost c : expenses) {
            total += c.getAmount();
        }
        return total;
    }

    public List<Cost> getCosts() {
        return expenses;
    }

    public JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Cost c : expenses) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

}
