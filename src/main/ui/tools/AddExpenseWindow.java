package ui.tools;

import model.Category;
import model.Cost;
import ui.BudgetAppGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExpenseWindow extends AddTransactionWindow {
    protected String type = "expense";
    protected String listType = "expenses";
    protected String[] catOptions = {"Bills","Debt repayments","One-time expenses","Miscellaneous purchases","For fun"};

    public AddExpenseWindow(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI, "expense");
        initializePanelSetup(catOptions);
        setUpAddButton();
    }

    protected void setUpAddButton() {
        addButton.setActionCommand("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Add")) {

                    category = (String) categoryOptions.getSelectedItem();
                    description = descriptionField.getText();
                    amount = Double.parseDouble(amountField.getText());

                    Category cat = getCostCatFromString(category);

                    BudgetAppGUI.getUser().addCost(new Cost(cat, description, amount));

                    frame.dispose();

                    successMessage(type, listType);
                }
            }
        });
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
