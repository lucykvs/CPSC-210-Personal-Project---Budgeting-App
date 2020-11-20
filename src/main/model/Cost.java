package model;

import persistence.Writable;

// Represents a cost having a category, description, total amount, and type expense
public class Cost extends Transaction implements Writable {

    // EFFECTS: constructs a cost with an associated category, description, and amount
    public Cost(Category category, String description, double amount) {
        super(category, description, amount);
        setType("expense");
    }
}
