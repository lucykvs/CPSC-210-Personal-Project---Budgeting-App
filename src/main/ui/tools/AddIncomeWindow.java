package ui.tools;

import model.Category;
import model.Fund;
import ui.BudgetAppGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddIncomeWindow extends AddTransactionWindow {
    protected String type = "source of income";
    protected String listType = "incomes";
    protected String[] catOptions = {"Employment", "Loan", "Gift", "Other"};

    public AddIncomeWindow(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI, "income");
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

                    Category cat = getFundCatFromString(category);

                    BudgetAppGUI.getUser().addFund(new Fund(cat, description, amount));

                    frame.dispose();

                    successMessage(type, listType);
                }
            }
        });
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
