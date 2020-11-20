package model;

// Enumeration of possible transaction categories
public enum Category {
    BILLS("Bills"), DEBT_REPAYMENTS("Debt repayments"),
    ONE_TIME_EXPENSES("One-time expenses"), MISCELLANEOUS_PURCHASES("Miscellaneous purchases"),
    FOR_FUN("For fun"), EMPLOYMENT("Employment"), LOAN("Loan"),
    GIFT("Gift"), OTHER("Other");

    public final String label;

    // EFFECTS: sets this label to label passed in Category initializer;
    Category(String label) {
        this.label = label;
    }

    // EFFECTS: returns label of given category
    public static String getCatString(Category c) {
        return c.label;
    }

    // EFFECTS: returns category that matches given label
    public static Category valueOfLabel(String label) {
        for (Category c : values()) {
            if (c.label.equals(label)) {
                return c;
            }
        }
        return null;
    }
}


