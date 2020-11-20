package ui.tools;

import model.Transaction;
import ui.BudgetAppGUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// Represents a window in which details of a transaction can be entered
public abstract class TransactionWindow implements ActionListener {
    protected BudgetAppGUI budgetAppGUI;
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
    protected JButton removeButton;
    protected JFrame frame;
    protected JPanel panel;
    private JPanel layout;
    private JPanel btnPanel;
    private String type;
    private String typeCapitalized;
    private String[] catOptions;

    // Constructs a window where transaction details can be entered
    public TransactionWindow(BudgetAppGUI budgetAppGUI, String title, String type) {
        frame = new JFrame(title);
        this.type = type;
        this.budgetAppGUI = budgetAppGUI;
    }

    // MODIFIES: this
    // EFFECTS: initializes layout of TransactionWindow
    protected void initializePanelSetup(String[] catOptions) {
        this.catOptions = catOptions;

        panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(1, 1, 1, 1));
        layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(2, 3, 2, 3));
        btnPanel = new JPanel(new GridLayout(7, 1, 0, 0));
        initializePanelGraphics();
    }

    // MODIFIES: this
    // EFFECTS: adds graphics component to frame
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

    // EFFECTS: creates labels and buttons to add to frame
    protected void createLabelsAndButtons() {
        addTransactionMessage = new JLabel(typeCapitalized + " details:");
        vspace = new JPanel(null);

        catLabel = new JLabel("Category:");
        categoryOptions = new JComboBox(catOptions);

        descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField(20);

        amountLabel = new JLabel("Amount:");
        amountField = new JTextField(10);

        if (frame.getTitle().startsWith("Add")) {
            addButton = new JButton("Add transaction");
            addButton.setActionCommand("Add");
            addButton.addActionListener(this);
        } else if (frame.getTitle().startsWith("Rem")) {
            removeButton = new JButton("Remove transaction");
            removeButton.setActionCommand("Remove");
            removeButton.addActionListener(this);
        }
    }

    // EFFECTS: gets details of transaction entered when add button is pressed
    protected void getTransactionDetailsFromTransactionWindow(ActionEvent e) {
        if (e.getActionCommand().equals("Add") || e.getActionCommand().equals("Remove")) {

            category = (String) categoryOptions.getSelectedItem();
            description = descriptionField.getText();
            amount = Double.parseDouble(amountField.getText());
        }
    }

    // MODIFIES: budgetTable
    // EFFECTS: updates budgetTable with new transaction in budgetAppGUI
    protected void updateUIWithTransactionFromDetailsEntered(String listType) {
        budgetAppGUI.updateTableWithAddedTransaction();
        playAddTransactionSound();

        frame.dispose();

        responseMessage("Successfully added " + type + " to your list of " + listType + ".");
    }

    // MODIFIES: budgetTable
    // EFFECTS: updates budgetTable with transaction to be removed
    protected void removeTransactionFromDetailsEntered(Transaction t, String listType) {
        if (budgetAppGUI.updateTableWithRemovedTransaction(t)) {
            responseMessage("Successfully removed " + type + " from your list of " + listType + ".");
        } else {
            responseMessage("Could not find " + type + " in your list of " + listType + ".");
        }
        frame.dispose();
    }

    // EFFECTS: plays cash register sound when transaction is added
    protected void playAddTransactionSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("data/AddTransactionSound.wav").getAbsoluteFile());

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error playing AddTransactionSound.");
            e.printStackTrace();
        }
    }

    // EFFECTS: generates success or failure message after transaction is added or removed
    protected void responseMessage(String message) {
        JDialog successMessage = new JDialog();

        successMessage.setLocationRelativeTo(null);
        successMessage.add(new JLabel(message));
        successMessage.setSize(400, 100);
        successMessage.setVisible(true);
    }

    // MODIFIES: btnPanel
    // EFFECTS: adds labels and text fields to panel of transaction window
    protected void addFeaturesToButtonPanel() {
        btnPanel.add(addTransactionMessage);
        btnPanel.add(vspace);

        btnPanel.add(catLabel);
        btnPanel.add(categoryOptions);

        btnPanel.add(descriptionLabel);
        btnPanel.add(descriptionField);

        btnPanel.add(amountLabel);
        btnPanel.add(amountField);

        if (frame.getTitle().startsWith("Add")) {
            btnPanel.add(addButton);
        } else if (frame.getTitle().startsWith("Remove")) {
            btnPanel.add(removeButton);
        }
    }

    // EFFECTS: sets location bounds of labels and text fields in this window
    protected void setFeatureBounds() {
        addTransactionMessage.setBounds(10, 20, 80, 25);
        vspace.setBounds(10, 45, 80, 20);

        catLabel.setBounds(10, 65, 80, 25);
        categoryOptions.setBounds(100, 65, 80, 25);

        descriptionLabel.setBounds(10, 110, 80, 25);
        descriptionField.setBounds(100, 110, 80, 25);

        amountLabel.setBounds(10, 155, 80, 25);
        amountField.setBounds(100, 155, 80, 25);

        if (frame.getTitle().startsWith("Add")) {
            addButton.setBounds(100, 200, 40, 25);
        } else if (frame.getTitle().startsWith("Rem")) {
            removeButton.setBounds(100, 200, 40, 25);
        }
    }
}



