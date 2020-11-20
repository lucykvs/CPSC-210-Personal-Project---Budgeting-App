package ui.tools;

import persistence.JsonReader;
import persistence.JsonWriter;
import ui.BudgetAppGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartUpWindow extends JFrame implements ActionListener {
    private JButton createNew;
    private JButton loadUser;
    private JLabel openingMessage;
    private static final String JSON_STORE = "./data/budget.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    //public User user;
    private JDialog userMessage;

    public StartUpWindow() {
        super("Budget Application");
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(1, 1, 1, 1));
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(2, 3, 2, 3));
        JPanel btnPanel = new JPanel(new GridLayout(5, 1, 0, 0));
        initializeGraphics();
        addButtonsToPanel(btnPanel);
        setPanelBehaviour(layout, panel, btnPanel);
    }

    private void initializeGraphics() {
        openingMessage = new JLabel("Welcome to your budgeting application! Select an option:");

        createNew = new JButton("Create new user");
        createNew.setActionCommand("Create new");
        createNew.addActionListener(this);
        add(createNew);

        loadUser = new JButton("Load previous user");
        loadUser.setActionCommand("Load");
        loadUser.addActionListener(this);
        add(loadUser);
    }

    private void addButtonsToPanel(JPanel btnPanel) {
        JPanel vspace1 = new JPanel(null);
        JPanel vspace2 = new JPanel(null);

        btnPanel.add(openingMessage);
        btnPanel.add(vspace2);
        btnPanel.add(createNew);
        btnPanel.add(vspace1);
        btnPanel.add(loadUser);
    }

    private void setPanelBehaviour(JPanel layout, JPanel panel, JPanel btnPanel) {
        layout.add(btnPanel);
        panel.add(layout, BorderLayout.CENTER);

        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(350, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("Create new")) {
            createNewUser();
        } else {
            loadPreviousUser();
        }
    }

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
        userMessage.add(new JLabel("Successfully created new user: " + BudgetAppGUI.getUser().getName() + "."));
        userMessage.setSize(100,100);
        dispose();
        new BudgetAppGUI().setVisible(true);
        userMessage.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: loads user and budget information from file
    public void loadPreviousUser() {
        jsonReader = new JsonReader(JSON_STORE);

        try {
            BudgetAppGUI.setUser(jsonReader.read());
            userMessage = new JDialog(this);
            userMessage.setMinimumSize(new Dimension(400, 100));
            userMessage.setLocationRelativeTo(null);
            userMessage.add(new JLabel(new StringBuilder().append("Successfully loaded ")
                    .append(BudgetAppGUI.getUser().getName()).append("'s budget from ").append(JSON_STORE).toString()));
            userMessage.setSize(100,100);
            dispose();
            new BudgetAppGUI().setVisible(true);
            userMessage.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
