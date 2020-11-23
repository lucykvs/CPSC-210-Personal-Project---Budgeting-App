package ui.tools;

import com.sun.org.apache.xpath.internal.operations.Neg;
import model.Category;
import model.NegativeAmountException;
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
        try {
            getTransactionDetailsFromTransactionWindow(e);
            addExpenseFromDetailsEntered();
        } catch (NumberFormatException ex1) {
            responseMessage(ex1.getMessage());
        } catch (NegativeAmountException ex2) {
            responseMessage("Amount cannot be negative.");
        }
    }

    // MODIFIES: BudgetAppGUI
    // EFFECTS: from details entered, adds expense to user's budget
    protected void addExpenseFromDetailsEntered() {
        try {
            Category cat = getCostCatFromString(category);
            budgetAppGUI.getUser().addCost(cat, description, amount);
            updateUIWithTransactionFromDetailsEntered(listType);
        } catch (NegativeAmountException e) {
            responseMessage("Amount cannot be negative.");
        }
    }

    // EFFECTS: returns category associated with given label
    public Category getCostCatFromString(String s) {
        return Category.valueOfLabel(s);
    }
}
