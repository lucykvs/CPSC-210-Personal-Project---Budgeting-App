package model;

public enum Category {
    BILLS("Bills"), DEBT_REPAYMENTS("Debt repayments"),
    ONE_TIME_EXPENSES("One-time expenses"), MISCELLANEOUS_PURCHASES("Miscellaneous purchases"),
    FOR_FUN("For fun"), EMPLOYMENT("Employment"), LOAN("Loan"),
    GIFT("Gift"), OTHER("Other");

    public final String label;

    Category(String label) {
        this.label = label;
    }

    // EFFECTS: converts Category to more appealing string
    public static String getCatString(Category c) {
        return c.label;
    }

    public static Category valueOfLabel(String label) {
        for (Category c : values()) {
            if (c.label.equals(label)) {
                return c;
            }
        }
        return null;
    }
}


