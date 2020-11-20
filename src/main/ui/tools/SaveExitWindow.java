package ui.tools;

import persistence.JsonWriter;
import ui.BudgetAppGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Represents window that displays save/exit options
public class SaveExitWindow extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/budget.json";
    private JsonWriter jsonWriter;
    private JDialog saveExitPopup;
    protected BudgetAppGUI budgetAppGUI;

    // EFFECTS: constructs new save and exit window
    public SaveExitWindow(BudgetAppGUI budgetAppGUI) {
        super("Save/Exit");
        this.budgetAppGUI = budgetAppGUI;
        initializePanel();
    }

    // EFFECTS: creates panels for layout of save exit window
    private void initializePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(1, 1, 1, 1));
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(2, 3, 2, 3));
        JPanel btnPanel = new JPanel(new GridLayout(7, 1, 0, 0));
        createButtons(btnPanel);
        setPanelBehaviour(btnPanel, layout, panel);
    }

    // EFFECTS: creates and adds buttons for save/exit options to this window's panel
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

    // EFFECTS: adds save/exit buttons to panel
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

    // EFFECTS: sets graphic details of window and its panels
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

    // EFFECTS: handles button clicks of save/exit buttons
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
            jsonWriter.write(budgetAppGUI.getUser());
            jsonWriter.close();
            savedMessage();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints message when user is saved successfully
    private void savedMessage() {
        JDialog savedMessage = new JDialog();

        savedMessage.setMinimumSize(new Dimension(400, 100));
        savedMessage.setLocationRelativeTo(null);
        savedMessage.add(new JLabel(new StringBuilder().append("Successfully saved ").append(budgetAppGUI.getUser()
                .getName()).append("'s budget to ").append(JSON_STORE).append(".").toString()));
        savedMessage.setSize(100, 100);
        savedMessage.setVisible(true);
    }
}

