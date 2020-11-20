package ui.tools;

import model.User;
import persistence.JsonReader;
import ui.BudgetAppGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Represents window that opens on startup and displays user's options
public class StartUpWindow extends JFrame implements ActionListener {
    private JButton createNew;
    private JButton loadUser;
    private JButton loadDemoUser;
    private JLabel openingMessage;
    private static final String JSON_STORE = "./data/budget.json";
    private static final String JSON_DEMO = "./data/demoBudget.json";
    private JsonReader jsonReader;
    private JDialog userMessage;

    // EFFECTS: constructs startup window
    public StartUpWindow() {
        super("Budget Application");
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(1, 1, 1, 1));
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(2, 3, 2, 3));
        JPanel btnPanel = new JPanel(new GridLayout(7, 1, 0, 0));
        initializeGraphics();
        addButtonsToPanel(btnPanel);
        setPanelBehaviour(layout, panel, btnPanel);
    }

    // EFFECTS: creates and adds buttons for load/create options to this window's panel
    private void initializeGraphics() {
        openingMessage = new JLabel("Welcome to your budgeting application! Select an option:");

        createNew = new JButton("Create new user");
        createNew.setActionCommand("Create new");
        createNew.addActionListener(this);
        add(createNew);

        loadUser = new JButton("Load previous user");
        loadUser.setActionCommand("Load previous");
        loadUser.addActionListener(this);
        add(loadUser);

        loadDemoUser = new JButton("Load demo user");
        loadDemoUser.setActionCommand("Load demo");
        loadDemoUser.addActionListener(this);
        add(loadDemoUser);
    }

    // EFFECTS: adds load/create buttons to panel
    private void addButtonsToPanel(JPanel btnPanel) {
        JPanel vspace1 = new JPanel(null);
        JPanel vspace2 = new JPanel(null);
        JPanel vspace3 = new JPanel(null);

        btnPanel.add(openingMessage);
        btnPanel.add(vspace2);
        btnPanel.add(createNew);
        btnPanel.add(vspace1);
        btnPanel.add(loadUser);
        btnPanel.add(vspace3);
        btnPanel.add(loadDemoUser);
    }

    // EFFECTS: sets graphic details of window and its panels
    private void setPanelBehaviour(JPanel layout, JPanel panel, JPanel btnPanel) {
        layout.add(btnPanel);
        panel.add(layout, BorderLayout.CENTER);

        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(350, 300);
        setVisible(true);
    }

    // EFFECTS: handles button clicks of load/create buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("Load previous")) {
            loadPreviousUser();
        } else if (cmd.equals("Load demo")) {
            loadDemoUser();
        } else {
            createNewUser();
        }
    }

    // MODIFIES: budgetAppGUI
    // EFFECTS: creates new user when create new button is clicked
    public void createNewUser() {
        userMessage = new JDialog();

        String username = (String)JOptionPane.showInputDialog(this,"Enter your desired username:",
                "Create new user",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,null
        );

        BudgetAppGUI.setUser(username);
        userMessage.setMinimumSize(new Dimension(400, 100));
        userMessage.setLocationRelativeTo(null);
        userMessage.add(new JLabel("Successfully created new user: " + username + "."));
        userMessage.setSize(100,100);
        dispose();
        new BudgetAppGUI().setVisible(true);
        userMessage.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: loads previous user from file when load button is clicked
    public void loadPreviousUser() {
        jsonReader = new JsonReader(JSON_STORE);

        try {
            User user = jsonReader.read();
            BudgetAppGUI.setUser(user);
            userMessage = new JDialog(this);
            userMessage.setMinimumSize(new Dimension(400, 100));
            userMessage.setLocationRelativeTo(null);
            userMessage.add(new JLabel(new StringBuilder().append("Successfully loaded ")
                    .append(user.getName()).append("'s budget from ").append(JSON_STORE).toString()));
            userMessage.setSize(100,100);
            dispose();
            new BudgetAppGUI().setVisible(true);
            userMessage.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads demo user from file when load demo button is clicked
    public void loadDemoUser() {
        jsonReader = new JsonReader(JSON_DEMO);

        try {
            User user = jsonReader.read();
            BudgetAppGUI.setUser(user);
            userMessage = new JDialog(this);
            userMessage.setMinimumSize(new Dimension(400, 100));
            userMessage.setLocationRelativeTo(null);
            userMessage.add(new JLabel(new StringBuilder().append("Successfully loaded ")
                    .append(user.getName()).append("'s budget from ").append(JSON_DEMO).toString()));
            userMessage.setSize(100,100);
            dispose();
            new BudgetAppGUI().setVisible(true);
            userMessage.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
