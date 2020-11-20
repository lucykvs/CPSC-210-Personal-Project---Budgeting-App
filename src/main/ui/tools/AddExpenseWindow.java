package ui.tools;

import model.Category;
import ui.BudgetAppGUI;

import java.awt.event.ActionEvent;

// Represents a window in which details of an expense can be entered
public class AddExpenseWindow extends TransactionWindow {
    protected String listType = "expenses";
    protected String[] catOptions = {"Bills","Debt repayments","One-time expenses","Miscellaneous purchases","For fun"};

    // EFFECTS: constructs new AddExpenseWindow
    public AddExpenseWindow(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI, "Add expense", "expense");
        initializePanelSetup(catOptions);
    }

    // EFFECTS: when add button is clicked, gets expense details entered and adds expense
    @Override
    public void actionPerformed(ActionEvent e) {
        getTransactionDetailsFromTransactionWindow(e);
        addExpenseFromDetailsEntered();
    }

    // MODIFIES: BudgetAppGUI
    // EFFECTS: from details entered, adds expense to user's budget
    protected void addExpenseFromDetailsEntered() {
        Category cat = getCostCatFromString(category);
        budgetAppGUI.getUser().addCost(cat, description, amount);
        updateUIWithTransactionFromDetailsEntered(listType);
    }

    // EFFECTS: returns category associated with given label
    public Category getCostCatFromString(String s) {
        return Category.valueOfLabel(s);
    }
}
