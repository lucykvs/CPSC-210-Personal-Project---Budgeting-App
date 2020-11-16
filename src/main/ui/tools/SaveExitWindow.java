package ui.tools;

import model.Budget;
import model.User;
import persistence.JsonWriter;
import ui.BudgetAppGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SaveExitWindow extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/budget.json";
    private JsonWriter jsonWriter;
    private JDialog saveExitPopup;
    protected BudgetAppGUI budgetAppGUI;

    public SaveExitWindow(BudgetAppGUI budgetAppGUI) {
        super("Save/Exit");
        this.budgetAppGUI = budgetAppGUI;

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(1, 1, 1, 1));
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(2, 3, 2, 3));
        JPanel btnPanel = new JPanel(new GridLayout(7, 1, 0, 0));

        JLabel saveExitMessage = new JLabel("Save current user?");

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        saveButton.setActionCommand("Save");

        JButton saveAndExitButton = new JButton("Save and exit");
        saveAndExitButton.addActionListener(this);
        saveAndExitButton.setActionCommand("Save and exit");

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        exitButton.setActionCommand("Exit");

        JPanel vspace1 = new JPanel(null);
        JPanel vspace2 = new JPanel(null);
        JPanel vspace3 = new JPanel(null);
        JPanel vspace4 = new JPanel(null);

        btnPanel.add(saveExitMessage);
        btnPanel.add(vspace1);
        btnPanel.add(saveButton);
        btnPanel.add(vspace2);
        btnPanel.add(saveAndExitButton);
        btnPanel.add(vspace3);
        btnPanel.add(exitButton);

        layout.add(btnPanel);
        panel.add(layout, BorderLayout.CENTER);

        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(200, 250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Save":
                saveUser();
                break;
            case "Save and exit":
                saveUser();
                dispose();
                budgetAppGUI.dispose();
            case "Exit":
                dispose();
                budgetAppGUI.dispose();
        }
    }

    // EFFECTS: saves the user's budget information to a file
    private void saveUser() {
        jsonWriter = new JsonWriter(JSON_STORE);

        try {
            jsonWriter.open();
            jsonWriter.write(BudgetAppGUI.getUser());
            jsonWriter.close();
            savedMessage();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void savedMessage() {
        JDialog savedMessage = new JDialog();

        savedMessage.setMinimumSize(new Dimension(400, 100));
        savedMessage.setLocationRelativeTo(null);
        savedMessage.add(new JLabel(new StringBuilder().append("Successfully saved ").append(BudgetAppGUI.getUser()
                .getName()).append("'s budget to ").append(JSON_STORE).append(".").toString()));
        savedMessage.setSize(100, 100);
        savedMessage.setVisible(true);
    }
}
        //userMessage.add(new JLabel("Successfully created new user: " + user.getName() + "."));
        //userMessage.setSize(100,100);
        //dispose();
        //new BudgetAppGUI().setVisible(true);

