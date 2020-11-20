package ui.tools;

import model.Category;
import model.Cost;
import ui.BudgetAppGUI;

import java.awt.event.ActionEvent;

public class AddExpenseWindow extends TransactionWindow {
    protected String listType = "expenses";
    protected String[] catOptions = {"Bills","Debt repayments","One-time expenses","Miscellaneous purchases","For fun"};

    public AddExpenseWindow(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI, "Add expense", "expense");
        initializePanelSetup(catOptions);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getTransactionDetailsFromTransactionWindow(e);
        addExpenseFromDetailsEntered();
    }

    protected void addExpenseFromDetailsEntered() {
        Category cat = getCostCatFromString(category);
        budgetAppGUI.getUser().addCost(cat, description, amount);
        updateUIWithTransactionFromDetailsEntered(listType);
    }

    public Category getCostCatFromString(String s) {
        return Category.valueOfLabel(s);
    }
}
