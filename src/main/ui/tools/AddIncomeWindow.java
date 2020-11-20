package ui.tools;

import model.Category;
import model.Fund;
import ui.BudgetAppGUI;

import java.awt.event.ActionEvent;

public class AddIncomeWindow extends TransactionWindow {
    protected String listType = "incomes";
    protected String[] catOptions = {"Employment", "Loan", "Gift", "Other"};

    public AddIncomeWindow(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI, "Add income", "source of income");
        initializePanelSetup(catOptions);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getTransactionDetailsFromTransactionWindow(e);
        addIncomeFromDetailsEntered();
    }

    protected void addIncomeFromDetailsEntered() {
        Category cat = getFundCatFromString(category);
        budgetAppGUI.getUser().addFund(cat, description, amount);
        updateUIWithTransactionFromDetailsEntered(listType);
    }

    public Category getFundCatFromString(String s) {
        return Category.valueOfLabel(s);
    }
}
