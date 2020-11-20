package ui.tools;

import model.Category;
import model.Fund;
import ui.BudgetAppGUI;

import java.awt.event.ActionEvent;

// Represents a window in which details of an income to be removed can be entered
public class RemoveIncomeWindow extends TransactionWindow {
    protected String listType = "incomes";
    protected String[] catOptions = {"Employment", "Loan", "Gift", "Other"};

    // EFFECTS: constructs new remove income window
    public RemoveIncomeWindow(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI, "Remove income", "source of income");
        initializePanelSetup(catOptions);
    }

    // EFFECTS: when add button is clicked, gets income details entered and removes income
    @Override
    public void actionPerformed(ActionEvent e) {
        getTransactionDetailsFromTransactionWindow(e);
        removeIncomeFromDetailsEntered();
    }

    // MODIFIES: BudgetAppGUI
    // EFFECTS: from details entered, removes income from user's budget
    protected void removeIncomeFromDetailsEntered() {
        Category cat = getFundCatFromString(category);

        removeTransactionFromDetailsEntered(new Fund(cat, description, amount), listType);
    }

    // EFFECTS: returns category associated with given label
    public Category getFundCatFromString(String s) {
        return Category.valueOfLabel(s);
    }
}
