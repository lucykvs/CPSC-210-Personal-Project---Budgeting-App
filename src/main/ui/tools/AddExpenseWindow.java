package ui.tools;

import model.Category;
import model.Cost;
import ui.BudgetAppGUI;

import java.awt.event.ActionEvent;

public class AddExpenseWindow extends AddTransactionWindow {
    protected String type = "expense";
    protected String listType = "expenses";
    protected String[] catOptions = {"Bills","Debt repayments","One-time expenses","Miscellaneous purchases","For fun"};

    public AddExpenseWindow(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI, "expense");
        initializePanelSetup(catOptions);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getTransactionDetailsFromAddTransactionWindow(e);
        addExpenseFromDetailsEntered();
    }

    protected void addExpenseFromDetailsEntered() {
        Category cat = getCostCatFromString(category);

        addTransactionFromDetailsEntered(new Cost(cat, description, amount), listType);
    }

    public Category getCostCatFromString(String s) {
        Category costCat;
        switch (s) {
            case "Bills":
                costCat = Category.BILLS;
                break;
            case "Debt repayments":
                costCat = Category.DEBT_REPAYMENTS;
                break;
            case "One-time expenses":
                costCat = Category.ONE_TIME_EXPENSES;
                break;
            case "Miscellaneous expenses":
                costCat = Category.MISCELLANEOUS_PURCHASES;
                break;
            default:
                costCat = Category.FOR_FUN;
                break;
        }
        return costCat;
    }
}
