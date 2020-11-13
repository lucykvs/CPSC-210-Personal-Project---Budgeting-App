//package ui.tools;
//
//import model.Cost;
//import ui.BudgetAppGUI;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class AddExpenseTool extends Tool {
//    private Cost cost;
//
//    public AddExpenseTool(BudgetAppGUI budgetApp, JComponent parent) {
//        super(budgetApp, parent);
//        cost = null;
//    }
//
//    // EFFECTS:
//    protected void makeCost() {
//        cost = new Cost(category, description, amount);
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  creates new button and adds to parent
//    @Override
//    protected void createButton(JComponent parent) {
//        button = new JButton(getLabel());
//        button = customizeButton(button);
//    }
//
//
//}
