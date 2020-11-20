package ui.tools;

import model.Category;
import model.Cost;
import ui.BudgetAppGUI;

import java.awt.event.ActionEvent;

public class RemoveExpenseWindow extends TransactionWindow {
    protected String listType = "expenses";
    protected String[] catOptions = {"Bills","Debt repayments","One-time expenses","Miscellaneous purchases","For fun"};

    public RemoveExpenseWindow(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI, "Remove expense", "expense");
        initializePanelSetup(catOptions);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getTransactionDetailsFromTransactionWindow(e);
        removeExpenseFromDetailsEntered();
    }

    protected void removeExpenseFromDetailsEntered() {
        Category cat = getCostCatFromString(category);

        removeTransactionFromDetailsEntered(new Cost(cat, description, amount), listType);
    }

    public Category getCostCatFromString(String s) {
        return Category.valueOfLabel(s);
    }
}
