package ui.tools;

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
        initializePanel();
    }

    private void initializePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(1, 1, 1, 1));
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(2, 3, 2, 3));
        JPanel btnPanel = new JPanel(new GridLayout(7, 1, 0, 0));
        createButtons(btnPanel);
        setPanelBehaviour(btnPanel, layout, panel);
    }

    private void createButtons(JPanel btnPanel) {
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

        addButtonsToPanel(btnPanel, saveExitMessage, saveButton, saveAndExitButton, exitButton);
    }

    private void addButtonsToPanel(JPanel btnPanel, JLabel saveExitMessage, JButton saveButton,
                                   JButton saveAndExitButton, JButton exitButton) {
        JPanel vspace1 = new JPanel(null);
        JPanel vspace2 = new JPanel(null);
        JPanel vspace3 = new JPanel(null);

        btnPanel.add(saveExitMessage);
        btnPanel.add(vspace1);
        btnPanel.add(saveButton);
        btnPanel.add(vspace2);
        btnPanel.add(saveAndExitButton);
        btnPanel.add(vspace3);
        btnPanel.add(exitButton);
    }

    private void setPanelBehaviour(JPanel btnPanel, JPanel layout, JPanel panel) {
        layout.add(btnPanel);
        panel.add(layout, BorderLayout.CENTER);

        add(panel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(200, 250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Save":
                saveUser();
                dispose();
                break;
            case "Save and exit":
                saveUser();
                dispose();
                budgetAppGUI.dispose();
                System.exit(0);
            case "Exit":
                dispose();
                budgetAppGUI.dispose();
                System.exit(0);
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

