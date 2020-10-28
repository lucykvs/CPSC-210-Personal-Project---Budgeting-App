package model;

import java.util.ArrayList;
import java.util.List;

// Represents a collection of funds comprising a budget's incomes
public class Income {
    private List<Fund> income;

    // EFFECTS: constructs an empty collection of incomes
    public Income() {
        income = new ArrayList<>();
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: adds a new income to the collection of incomes to be counted
    public void addFund(FundCategory category, String description, double amount) {
        Fund f = new Fund(category, description, amount);
        income.add(f);
    }

    // MODIFIES: this
    // EFFECTS: returns list of the descriptions of those incomes in list of incomes;
    // list returned can be empty.
    public List<String> getAllFundDescriptions() {
        List<String> descriptions = new ArrayList<>();

        for (Fund f : income) {
            descriptions.add(f.getDescription());
        }
        return descriptions;
    }

    // MODIFIES: this
    // EFFECTS: returns total amount in dollars of all income amounts in list of incomes
    public double getTotalIncome() {
        double total = 0;

        for (Fund f : income) {
            total += f.getAmount();
        }
        return total;
    }
}