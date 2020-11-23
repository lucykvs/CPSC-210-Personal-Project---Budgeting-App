package ui.tools;

import model.Category;
import model.Cost;
import model.NegativeAmountException;
import ui.BudgetAppGUI;

import java.awt.event.ActionEvent;

// Represents a window in which details of an expense to be removed can be entered
public class RemoveExpenseWindow extends TransactionWindow {
    protected String listType = "expenses";
    protected String[] catOptions = {"Bills","Debt repayments","One-time expenses","Miscellaneous purchases","For fun"};

    // EFFECTS: constructs new remove expense window
    public RemoveExpenseWindow(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI, "Remove expense", "expense");
        initializePanelSetup(catOptions);
    }

    // EFFECTS: when add button is clicked, gets expense details entered and removes expense
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            getTransactionDetailsFromTransactionWindow(e);
            removeExpenseFromDetailsEntered();
        } catch (NumberFormatException ex1) {
            responseMessage(ex1.getMessage());
        } catch (NegativeAmountException ex2) {
            responseMessage("Amount cannot be negative.");
        }
    }

    // MODIFIES: BudgetAppGUI
    // EFFECTS: from details entered, removes expense from user's budget
    protected void removeExpenseFromDetailsEntered() {
        Category cat = getCostCatFromString(category);
        removeTransactionFromDetailsEntered(new Cost(cat, description, amount), listType);
    }

    // EFFECTS: returns category associated with given label
    public Category getCostCatFromString(String s) {
        return Category.valueOfLabel(s);
    }
}
