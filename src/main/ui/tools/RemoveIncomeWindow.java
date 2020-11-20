package ui.tools;

import model.Category;
import model.Cost;
import model.Fund;
import ui.BudgetAppGUI;

import java.awt.event.ActionEvent;

public class RemoveIncomeWindow extends TransactionWindow {
    protected String listType = "incomes";
    protected String[] catOptions = {"Employment", "Loan", "Gift", "Other"};

    public RemoveIncomeWindow(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI, "Remove income", "source of income");
        initializePanelSetup(catOptions);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getTransactionDetailsFromTransactionWindow(e);
        removeIncomeFromDetailsEntered();
    }

    protected void removeIncomeFromDetailsEntered() {
        Category cat = getFundCatFromString(category);

        removeTransactionFromDetailsEntered(new Fund(cat, description, amount), listType);
    }

    public Category getFundCatFromString(String s) {
        return Category.valueOfLabel(s);
    }
}
