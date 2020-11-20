package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.EnumSet;

// Represents a cost having a brief description and total amount in dollars
public class Cost extends Transaction implements Writable {
    public EnumSet<Category> costCategories;

    // EFFECTS: constructs a cost with an associated category, description, and amount
    public Cost(Category category, String description, double amount) {
        super(category, description, amount);
        costCategories = EnumSet.of(Category.BILLS, Category.DEBT_REPAYMENTS, Category.ONE_TIME_EXPENSES,
                Category.MISCELLANEOUS_PURCHASES, Category.FOR_FUN);
        setType("expense");
    }
}
