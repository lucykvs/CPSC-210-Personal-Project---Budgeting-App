package model;

import java.util.EnumSet;

public enum Category {
    BILLS, DEBT_REPAYMENTS, ONE_TIME_EXPENSES, MISCELLANEOUS_PURCHASES, FOR_FUN, EMPLOYMENT, LOAN, GIFT, OTHER;

    // EFFECTS: converts Category to more appealing string
    public static String getCatString(Category c) {
        String catString = "";
        switch (c) {
            case BILLS:
                catString = "Bills";
                break;
            case DEBT_REPAYMENTS:
                catString = "Debt repayments";
                break;
            case ONE_TIME_EXPENSES:
                catString = "One-time expenses";
                break;
            case MISCELLANEOUS_PURCHASES:
                catString = "Miscellaneous expenses";
                break;
            case FOR_FUN:
                catString = "For fun!";
                break;
            case EMPLOYMENT:
                catString = "Employment";
                break;
            case LOAN:
                catString = "Loan";
            case GIFT:
                catString = "Gift";
            default:
                catString = "Other";
        }
        return catString;
    }
}
