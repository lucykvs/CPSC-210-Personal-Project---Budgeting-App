package ui;

import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.AddExpenseWindow;
import ui.tools.SaveExitWindow;
import ui.tools.StartUpWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BudgetAppGUI extends JFrame implements ActionListener {
    private JPanel userPanel;
    private JPanel detailPanel;
    private JPanel buttonPanel;
    private JPanel budgetPanel;
    private static final String JSON_STORE = "./data/budget.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static User user;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    // EFFECTS: runs the budget application
    public BudgetAppGUI() {
        super("Budget Application");
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window where this BudgetApp will operate, and adds the buttons to be used to add to
    //          this budget
    private void initializeGraphics() {
        addComponentsToPane(getContentPane());

        pack();
    }

    public static void setUser(String username) {
        user = new User(username);
    }

    public static void setUser(User previousUser) {
        user = previousUser;
    }

    public static User getUser() {
        return user;
    }

    public void addComponentsToPane(Container pane) {
        createUserPanel(pane);
        createDetailPanel(pane);
        createBudgetPanel(pane);
        createButtonPanel(pane);
        setContentPaneLayout(pane);
    }

    private void setContentPaneLayout(Container pane) {
        GroupLayout contentPaneLayout = new GroupLayout(pane);

        pane.setLayout(contentPaneLayout);
        setHorizontalGroup(contentPaneLayout);
        setVerticalGroup(contentPaneLayout);

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(getOwner());
    }

    private void setVerticalGroup(GroupLayout contentPaneLayout) {
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(detailPanel, 200, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(userPanel, 200, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                        .addComponent(budgetPanel, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                                .addGap(0, 0, 0))
        );
    }

    private void setHorizontalGroup(GroupLayout contentPaneLayout) {
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(buttonPanel, 200, 200, 200)
                                        .addComponent(userPanel, GroupLayout.Alignment.TRAILING,
                                                200, 200, 200))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(detailPanel, GroupLayout.DEFAULT_SIZE,
                                                450, Short.MAX_VALUE)
                                        .addComponent(budgetPanel, GroupLayout.DEFAULT_SIZE,
                                                450, Short.MAX_VALUE)))
        );
    }

    private void createUserPanel(Container pane) {
        userPanel = new JPanel();
        userPanel.setMinimumSize(new Dimension(80, HEIGHT / 4));
        userPanel.setMaximumSize(new Dimension(WIDTH / 4, HEIGHT / 4));
        userPanel.setPreferredSize(new Dimension(WIDTH / 4, HEIGHT / 4));
        userPanel.setBorder(new javax.swing.border.CompoundBorder(BorderFactory.createBevelBorder(1),
                new javax.swing.border.TitledBorder(userPanel.getBorder(),"User:",javax.swing.border.TitledBorder
                .CENTER,javax.swing.border.TitledBorder.TOP,new Font("Arial",Font.BOLD,14),
                Color.blue)));

        userPanel.setLayout(new GridBagLayout());
        ((GridBagLayout)userPanel.getLayout()).columnWidths = new int[] {0, 0, 0};
        ((GridBagLayout)userPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
        ((GridBagLayout)userPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
        ((GridBagLayout)userPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

        addUserPhoto();
        pane.add(userPanel);
    }

    private void addUserPhoto() {
        try {
            BufferedImage fullSizeUserPic = ImageIO.read(new File("./data/tobs.jpg"));
            Image scaledUserPic = fullSizeUserPic.getScaledInstance(userPanel.getPreferredSize().width - 75,
                    userPanel.getPreferredSize().height - 20, Image.SCALE_SMOOTH);
            JLabel imagePanel = new JLabel(new ImageIcon(scaledUserPic));
            userPanel.add(imagePanel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES:
    private void createButtonPanel(Container pane) {
        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createBevelBorder(1));

        buttonPanel.setLayout(new GridLayout(7, 0));
        JPanel vspacer1 = new JPanel(null);
        JPanel vspacer2 = new JPanel(null);
        JPanel vspacer3 = new JPanel(null);

        //---- addExpenseButton ----
        JButton addExpenseButton = new JButton("Add expense");
        buttonPanel.add(addExpenseButton);
        buttonPanel.add(vspacer1);
        addExpenseButton.setActionCommand("Add expense");
        addExpenseButton.addActionListener(this);

        //---- addIncomeButton ----
        JButton addIncomeButton = new JButton("Add income");
        buttonPanel.add(addIncomeButton);
        buttonPanel.add(vspacer2);
        addIncomeButton.setActionCommand("Add income");
        addIncomeButton.addActionListener(this);

        //---- filterButton ----
        JButton filterButton = new JButton("Filter");
        buttonPanel.add(filterButton);
        buttonPanel.add(vspacer3);
        filterButton.setActionCommand("Filter");
        filterButton.addActionListener(this);

        // Save/exit button
        JButton saveExitButton = new JButton("Save/Exit");
        buttonPanel.add(saveExitButton);
        saveExitButton.setActionCommand("Save/Exit");
        saveExitButton.addActionListener(this);

        pane.add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add expense":
                new AddExpenseWindow(this);
                break;
            case "Add income":
                addIncomeToTable();
                break;
            case "Filter":
                filterTransactions();
                break;
            case "Save/Exit":
                new SaveExitWindow(this).setVisible(true);
                break;
        }
    }

    private void addExpenseToTable() {
    }

    private void addIncomeToTable() {
    }

    private void filterTransactions() {
    }

    private void createDetailPanel(Container pane) {
        detailPanel = new JPanel();
        detailPanel.setBorder(BorderFactory.createBevelBorder(1));

        detailPanel.setBorder(new javax.swing.border.CompoundBorder(BorderFactory.createBevelBorder(1),
                new javax.swing.border.TitledBorder(detailPanel.getBorder(),
                        "Budget Details:",javax.swing.border.TitledBorder.LEFT,
                        javax.swing.border.TitledBorder.TOP,new Font("Arial",Font.BOLD,14),
                        Color.blue)));

        detailPanel.setLayout(new GridBagLayout());
        ((GridBagLayout)detailPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
        ((GridBagLayout)detailPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

        pane.add(detailPanel);
    }

    private void createBudgetPanel(Container pane) {
        budgetPanel = new JPanel();
        budgetPanel.setBorder(BorderFactory.createBevelBorder(1));
        budgetPanel.setLayout(new GridLayout());


        JScrollPane scrollPane = new JScrollPane();

        budgetPanel.setBorder(new javax.swing.border.CompoundBorder(BorderFactory.createBevelBorder(1),
                new javax.swing.border.TitledBorder(budgetPanel.getBorder(),"Transactions:",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new Font("Arial",Font.BOLD,14), Color.blue)));

        String[] columnNames = {"Type",
                "Category",
                "Description",
                "Amount",
                "Date?"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        JTable table = new JTable(model);
        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding",5, false},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };

        JTable budgetTable = new JTable(data, columnNames);
        budgetTable.setColumnSelectionAllowed(true);
        budgetTable.setFillsViewportHeight(true);
        scrollPane.setViewportView(budgetTable);

        budgetPanel.add(scrollPane);
        pane.add(budgetPanel);
    }
}
