package ui.tools;

import model.Category;
import model.Fund;
import ui.BudgetAppGUI;

import java.awt.event.ActionEvent;

public class AddIncomeWindow extends AddTransactionWindow {
    protected String type = "source of income";
    protected String listType = "incomes";
    protected String[] catOptions = {"Employment", "Loan", "Gift", "Other"};

    public AddIncomeWindow(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI, "income");
        initializePanelSetup(catOptions);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getTransactionDetailsFromAddTransactionWindow(e);
        addIncomeFromDetailsEntered();
    }

    protected void addIncomeFromDetailsEntered() {
        Category cat = getFundCatFromString(category);

        addTransactionFromDetailsEntered(new Fund(cat, description, amount), listType);
    }

    public Category getFundCatFromString(String s) {
        Category fundCat;
        switch (s) {
            case "Employment":
                fundCat = Category.EMPLOYMENT;
                break;
            case "Loan":
                fundCat = Category.LOAN;
                break;
            case "Gift":
                fundCat = Category.GIFT;
                break;
            default:
                fundCat = Category.OTHER;
                break;
        }
        return fundCat;
    }
}
