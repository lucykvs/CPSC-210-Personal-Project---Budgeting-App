package main.model;

import java.util.ArrayList;
import java.util.List;

public class Expenses {
    private List<Cost> costs;

    // EFFECTS: constructs an empty collection of costs
    public Expenses() {
        costs = new ArrayList<>();
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: adds a new cost to the collection of expenses to be counted
    public void addCost(String description, double amount) {
        Cost c = new Cost(description, amount);
        costs.add(c);
    }

    // MODIFIES: this
    // EFFECTS: returns list of the descriptions of those costs in list of expenses;
    // list returned can be empty.
    public List<String> getAllCostDescriptions() {
        List<String> descriptions = new ArrayList<>();
        for (Cost c : costs) {
            descriptions.add(c.getDescription());
        }
        return descriptions;
    }

    // MODIFIES: this
    // EFFECTS: returns total amount in dollars of all cost amounts in list of expenses
    public double getTotalExpenses() {
        double total = 0;
        for (Cost c : costs) {
            total += c.getAmount();
        }

        return total;
    }



}
