package ui.tools;

import model.Budget;
import model.Cost;
import model.CostCategory;
import ui.BudgetAppGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class AddExpenseWindow extends JFrame implements ActionListener {
    protected BudgetAppGUI budgetAppGUI;
    private JComboBox<String> categoryOptions;
    private JTextField descriptionField;
    private JTextField amountField;
    private String category;
    private String description;
    private double amount;

    public AddExpenseWindow(BudgetAppGUI budgetAppGUI) {
        super("Add expense");

        this.budgetAppGUI = budgetAppGUI;

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(1, 1, 1, 1));
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(2, 3, 2, 3));
        JPanel btnPanel = new JPanel(new GridLayout(7, 1, 0, 0));

        JLabel addExpenseMessage = new JLabel("Expense details:");

        JLabel catLabel = new JLabel("Category:");
        String[] options = {"Bills", "Debt repayments", "One-time expenses", "Miscellaneous purchases", "For fun"};
        categoryOptions = new JComboBox(options);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField(20);

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(10);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(this);
        addButton.setActionCommand("Add");

        JPanel vspace1 = new JPanel(null);
        JPanel vspace2 = new JPanel(null);
        JPanel vspace3 = new JPanel(null);
        JPanel vspace4 = new JPanel(null);

        addExpenseMessage.setBounds(10, 20, 80, 25);
        btnPanel.add(addExpenseMessage);

        vspace1.setBounds(10, 45, 180, 20);
        btnPanel.add(vspace1);

        catLabel.setBounds(10, 65, 80, 25);
        categoryOptions.setBounds(100, 65, 80, 25);
        btnPanel.add(catLabel);
        btnPanel.add(categoryOptions);

        vspace2.setBounds(10, 90, 810, 20);

        descriptionLabel.setBounds(10, 110, 80, 25);
        descriptionField.setBounds(100, 110, 80, 25);
        btnPanel.add(descriptionLabel);
        btnPanel.add(descriptionField);

        vspace3.setBounds(10, 135, 180, 20);

        amountLabel.setBounds(10, 155, 80, 25);
        amountField.setBounds(100, 155, 80, 25);
        btnPanel.add(amountLabel);
        btnPanel.add(amountField);

        vspace4.setBounds(10, 180, 180, 20);

        addButton.setBounds(100, 200, 40, 25);
        btnPanel.add(addButton);

        layout.add(btnPanel);
        panel.add(layout, BorderLayout.CENTER);

        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {

            category = (String)categoryOptions.getSelectedItem();
            description = descriptionField.getText();
            amount = Double.parseDouble(amountField.getText());

            CostCategory cat = getCostCatFromString(category);

            BudgetAppGUI.getUser().addCost(new Cost(cat, description, amount));
            dispose();

            successMessage("cost", "expenses");
        }
    }

    public CostCategory getCostCatFromString(String s) {
        CostCategory costCat;

        switch (s) {
            case "Bills":
                costCat = CostCategory.BILLS;
                break;
            case "Debt repayments":
                costCat = CostCategory.DEBT_REPAYMENTS;
                break;
            case "One-time expenses":
                costCat = CostCategory.ONE_TIME_EXPENSES;
                break;
            case "Miscellaneous expenses":
                costCat = CostCategory.MISCELLANEOUS_PURCHASES;
                break;
            default:
                costCat = CostCategory.FOR_FUN;
                break;
        }
        return costCat;
    }

    private void successMessage(String type, String listType) {
        JDialog successMessage = new JDialog();

        //successMessage.setMinimumSize(new Dimension(400, 100));
        successMessage.setLocationRelativeTo(null);
        successMessage.add(new JLabel("Successfully added " + type + " to your list of " + listType + "."));
        successMessage.setSize(300, 100);
        successMessage.setVisible(true);
    }
}
