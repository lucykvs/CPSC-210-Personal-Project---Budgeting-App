package ui.tools;

import model.Category;
import ui.BudgetAppGUI;

import java.awt.event.ActionEvent;

// Represents a window in which details of an income can be entered
public class AddIncomeWindow extends TransactionWindow {
    protected String listType = "incomes";
    protected String[] catOptions = {"Employment", "Loan", "Gift", "Other"};

    // EFFECTS: constructs new AddIncomeeWindow
    public AddIncomeWindow(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI, "Add income", "source of income");
        initializePanelSetup(catOptions);
    }

    // EFFECTS: when add button is clicked, gets income details entered and adds income
    @Override
    public void actionPerformed(ActionEvent e) {
        getTransactionDetailsFromTransactionWindow(e);
        addIncomeFromDetailsEntered();
    }

    // MODIFIES: BudgetAppGUI
    // EFFECTS: from details entered, adds income to user's budget
    protected void addIncomeFromDetailsEntered() {
        Category cat = getFundCatFromString(category);
        budgetAppGUI.getUser().addFund(cat, description, amount);
        updateUIWithTransactionFromDetailsEntered(listType);
    }

    // EFFECTS: returns category associated with given label
    public Category getFundCatFromString(String s) {
        return Category.valueOfLabel(s);
    }
}
