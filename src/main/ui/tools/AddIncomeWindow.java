package ui.tools;

import model.Category;
import model.NegativeAmountException;
import ui.BudgetAppGUI;

import java.awt.event.ActionEvent;

// Represents a window in which details of an income can be entered
public class AddIncomeWindow extends TransactionWindow {
    protected String listType = "incomes";
    protected String[] catOptions = {"Employment", "Loan", "Gift", "Other"};

    // EFFECTS: constructs new AddIncomeWindow
    public AddIncomeWindow(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI, "Add income", "source of income");
        initializePanelSetup(catOptions);
    }

    // EFFECTS: when add button is clicked, gets income details entered and adds income. If amount entered is not
    //          a number, catches NumberFormatException and prints message to user; if amount is negative, catches
    //          NegativeAmountException and prints appropriate message to user.
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            getTransactionDetailsFromTransactionWindow(e);
            addIncomeFromDetailsEntered();
        } catch (NumberFormatException ex1) {
            responseMessage(ex1.getMessage());
        } catch (NegativeAmountException ex2) {
            responseMessage("Amount cannot be negative.");
        }
    }

    // MODIFIES: BudgetAppGUI
    // EFFECTS: from details entered, adds income to user's budget
    protected void addIncomeFromDetailsEntered() {
        try {
            Category cat = getFundCatFromString(category);
            budgetAppGUI.getUser().addFund(cat, description, amount);
            updateUIWithTransactionFromDetailsEntered(listType);
        } catch (NegativeAmountException e) {
            responseMessage("Amount cannot be negative.");
        }
    }

    // EFFECTS: returns category associated with given label
    public Category getFundCatFromString(String s) {
        return Category.valueOfLabel(s);
    }
}
