package main.model;

import java.util.ArrayList;
import java.util.List;

public class Incomes {
    private List<Income> incomes;

    // EFFECTS: constructs an empty collection of incomes
    public Incomes() {
        incomes = new ArrayList<>();
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: adds a new income to the collection of incomes to be counted
    public void addIncome(String description, double amount) {
        Income i = new Income(description, amount);
        incomes.add(i);
    }

    // MODIFIES: this
    // EFFECTS: returns list of the descriptions of those incomes in list of incomes;
    // list returned can be empty.
    public List<String> getAllIncomeDescriptions() {
        List<String> descriptions = new ArrayList<>();
        for (Income i : incomes) {
            descriptions.add(i.getDescription());
        }
        return descriptions;
    }

    // MODIFIES: this
    // EFFECTS: returns total amount in dollars of all income amounts in list of incomes
    public double getTotalIncome() {
        double total = 0;
        for (Income i : incomes) {
            total += i.getAmount();
        }

        return total;
    }



}