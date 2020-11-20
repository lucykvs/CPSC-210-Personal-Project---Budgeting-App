package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.EnumSet;

// Represents a source of income having a brief description and total amount in dollars
public class Fund extends Transaction implements Writable {

    // EFFECTS: constructs an income with an associated description and amount
    public Fund(Category category, String description, double amount) {
        super(category, description, amount);
        setType("income");
    }
}
