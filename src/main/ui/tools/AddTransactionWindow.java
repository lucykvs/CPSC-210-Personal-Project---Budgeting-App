package ui.tools;

import model.Cost;
import ui.BudgetAppGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AddTransactionWindow {
    private BudgetAppGUI budgetAppGUI;
    protected JComboBox<String> categoryOptions;
    protected JTextField descriptionField;
    protected JTextField amountField;
    protected String category;
    protected String description;
    protected Double amount;
    private JLabel catLabel;
    private JLabel descriptionLabel;
    private JLabel amountLabel;
    private JLabel addTransactionMessage;
    private JPanel vspace;
    protected JButton addButton;
    protected JFrame frame;
    protected JPanel panel;
    private JPanel layout;
    private JPanel btnPanel;
    private String type;
    private String typeCapitalized;
    private String[] catOptions;


    public AddTransactionWindow(BudgetAppGUI budgetAppGUI, String type) {
        frame = new JFrame("Add " + type + ":");
        this.type = type;
        this.budgetAppGUI = budgetAppGUI;
    }

    protected void initializePanelSetup(String[] catOptions) {
        this.catOptions = catOptions;

        panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(1, 1, 1, 1));
        layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(2, 3, 2, 3));
        btnPanel = new JPanel(new GridLayout(7, 1, 0, 0));
        initializePanelGraphics();
    }

    protected void initializePanelGraphics() {
        String l1 = type.substring(0, 1).toUpperCase();
        typeCapitalized = l1 + type.substring(1);

        createLabelsAndButtons();
        setFeatureBounds();
        addFeaturesToButtonPanel();

        layout.add(btnPanel);
        panel.add(layout, BorderLayout.CENTER);

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(400, 250));
        frame.setSize(400, 250);
        frame.setVisible(true);
    }

    protected void createLabelsAndButtons() {
        addTransactionMessage = new JLabel(typeCapitalized + " details:");
        vspace = new JPanel(null);

        catLabel = new JLabel("Category:");
        categoryOptions = new JComboBox(catOptions);

        descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField(20);

        amountLabel = new JLabel("Amount:");
        amountField = new JTextField(10);

        addButton = new JButton("Add");
    }

    protected void addFeaturesToButtonPanel() {
        btnPanel.add(addTransactionMessage);
        btnPanel.add(vspace);

        btnPanel.add(catLabel);
        btnPanel.add(categoryOptions);

        btnPanel.add(descriptionLabel);
        btnPanel.add(descriptionField);

        btnPanel.add(amountLabel);
        btnPanel.add(amountField);

        btnPanel.add(addButton);
    }

    protected void setFeatureBounds() {
        addTransactionMessage.setBounds(10, 20, 80, 25);
        vspace.setBounds(10,45,80,20);

        catLabel.setBounds(10, 65, 80, 25);
        categoryOptions.setBounds(100, 65, 80, 25);

        descriptionLabel.setBounds(10, 110, 80, 25);
        descriptionField.setBounds(100, 110, 80, 25);

        amountLabel.setBounds(10, 155, 80, 25);
        amountField.setBounds(100, 155, 80, 25);

        addButton.setBounds(100, 200, 40, 25);
    }

    protected void successMessage(String type, String listType) {
        JDialog successMessage = new JDialog();

        successMessage.setLocationRelativeTo(null);
        successMessage.add(new JLabel("Successfully added " + type + " to your list of " + listType + "."));
        successMessage.setSize(300, 100);
        successMessage.setVisible(true);
    }
}
