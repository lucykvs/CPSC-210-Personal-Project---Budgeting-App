package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.EnumSet;

// Represents a cost having a brief description and total amount in dollars
public class Cost extends Transaction implements Writable {

    // EFFECTS: constructs a cost with an associated category, description, and amount
    public Cost(Category category, String description, double amount) {
        super(category, description, amount);
        setType("expense");
    }
}
