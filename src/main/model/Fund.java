package model;

import persistence.Writable;

// Represents a source of income having a category, description, amount, and type income
public class Fund extends Transaction implements Writable {

    // EFFECTS: constructs an income with an associated description and amount
    public Fund(Category category, String description, double amount) {
        super(category, description, amount);
        setType("income");
    }
}
