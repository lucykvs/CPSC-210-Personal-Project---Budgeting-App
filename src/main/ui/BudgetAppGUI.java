package ui;

import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;

public class BudgetAppGUI extends JFrame {
    private static final String JSON_STORE = "./data/budget.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JPanel panel;
    private JLabel label;
    private User user;
//    jsonWriter = new JsonWriter(JSON_STORE);
//    jsonReader = new JsonReader(JSON_STORE);
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;

    private JButton button;
    private GridBagConstraints cons;

    // EFFECTS: runs GUI of budget application
    public static void main(String[] args) {
        new BudgetAppGUI();
    }

    // EFFECTS: runs the budget application
    public BudgetAppGUI() {
        super("Budget Application");
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window where this BudgetApp will operate, and adds the buttons to be used to add to
    //          this budget
    private void initializeGraphics() {
        setLocation(0,0);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponentsToPane(getContentPane());

        pack();
        setVisible(true);
    }

    private void addComponentsToPane(Container pane) {
        pane.setLayout(new GridBagLayout());

        createUserPanel(pane);
        createDetailPanel(pane);
        createBudgetPanel(pane);
        createButtonPanel(pane);
        createSaveExitPanel(pane);
    }

    private void createUserPanel(Container pane) {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createBevelBorder(1));

        label = new JLabel("User:");
        cons = new GridBagConstraints();

        cons.weightx = 0.25;    // request this weight of extra horizontal space
        cons.weighty = 0.35;   // request this weight of extra vertical space
        cons.fill = GridBagConstraints.BOTH;
        cons.gridx = 0;        // place on grid
        cons.gridy = 0;        // place on grid

        panel.add(label);
        pane.add(panel, cons);
    }

    // MODIFIES:
    private void createButtonPanel(Container pane) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createBevelBorder(1));

        JButton exButton = new JButton("Add expense");
        JButton inButton = new JButton("Add income");
        JButton filtButton = new JButton("Filter");
        cons = new GridBagConstraints();

        cons.fill = GridBagConstraints.BOTH;
        cons.weightx = 0.25;
        cons.weighty = 0.65;
        cons.gridwidth = 2;
        cons.gridx = 0;
        cons.gridy = 1;

        buttonPanel.add(Box.createRigidArea(new Dimension(buttonPanel.getWidth() / 2,100)));
        buttonPanel.add(exButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5,60)));
        buttonPanel.add(inButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5,60)));
        buttonPanel.add(filtButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5,60)));

        pane.add(buttonPanel, cons);
    }

    private void createDetailPanel(Container pane) {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createBevelBorder(1));

        label = new JLabel("Balance:");
        cons = new GridBagConstraints();

        cons.fill = GridBagConstraints.BOTH;
        cons.weightx = 0.75;
        cons.gridx = 1;
        cons.gridy = 0;
        panel.add(label);
        pane.add(panel, cons);
    }

    private void createBudgetPanel(Container pane) {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createBevelBorder(1));

        label = new JLabel("Transactions:");
        cons = new GridBagConstraints();

        cons.fill = GridBagConstraints.BOTH;
        cons.weighty = 0.75;
        cons.gridwidth = 2;
        cons.gridx = 1;
        cons.gridy = 1;
        panel.add(label);
        pane.add(panel, cons);
    }

    public void createSaveExitPanel(Container pane) {
        button = new JButton("5");
        cons = new GridBagConstraints();

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.ipady = 0;       //reset to default
        cons.weighty = 0;     //request no extra vertical space
        cons.anchor = GridBagConstraints.PAGE_END; //bottom of space
        cons.insets = new Insets(10,0,0,0);  //top padding
        cons.gridx = 0;       //aligned with button 2
        cons.gridwidth = 2;   //2 columns wide
        cons.gridy = 2;       //third row
        pane.add(button, cons);
    }

}
