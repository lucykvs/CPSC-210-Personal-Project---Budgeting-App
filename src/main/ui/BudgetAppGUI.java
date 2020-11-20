package ui;

import model.Category;
import model.Transaction;
import model.User;
import ui.tools.AddExpenseWindow;
import ui.tools.AddIncomeWindow;
import ui.tools.SaveExitWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;

public class BudgetAppGUI extends JFrame implements ActionListener {
    private JPanel userPanel;
    private JPanel detailPanel;
    private JPanel buttonPanel;
    private JPanel budgetPanel;
    private JTable budgetTable;
    private DefaultTableModel tableModel;
    private static User user;
    private String[] columnNames;
    private Object[][] transactionArray;
    private Container pane;

    private EnumSet<Category> costCategories = EnumSet.of(Category.BILLS,Category.DEBT_REPAYMENTS,
            Category.ONE_TIME_EXPENSES, Category.MISCELLANEOUS_PURCHASES, Category.FOR_FUN);
    private EnumSet<Category> fundCategories = EnumSet.of(Category.EMPLOYMENT,Category.LOAN, Category.GIFT,
            Category.OTHER);

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    // EFFECTS: runs the budget application
    public BudgetAppGUI() {
        super("Budget Application");
        initializeGraphics();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window where this BudgetApp will operate, and adds the buttons to be used to add to
    //          this budget
    private void initializeGraphics() {
        pane = getContentPane();

        addComponentsToPane();

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

    public void addComponentsToPane() {
        createUserPanel();
        createDetailPanel();
        createBudgetPanel();
        createButtonPanel();
        setContentPaneLayout();
    }

    private void setContentPaneLayout() {
        GroupLayout contentPaneLayout = new GroupLayout(pane);

        pane.setLayout(contentPaneLayout);
        setHorizontalGroup(contentPaneLayout);
        setVerticalGroup(contentPaneLayout);

        pack();
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

    private void createUserPanel() {
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
    private void createButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createBevelBorder(1));

        buttonPanel.setLayout(new GridLayout(7, 0));
        JPanel vspacer1 = new JPanel(null);
        JPanel vspacer2 = new JPanel(null);
        JPanel vspacer3 = new JPanel(null);

        createAddExpenseButton(vspacer1);
        createAddIncomeButton(vspacer2);
        createFilterButton(vspacer3);
        createSaveExitButton();
    }

    private void createAddExpenseButton(JPanel vspacer1) {
        //---- addExpenseButton ----
        JButton addExpenseButton = new JButton("Add expense");
        buttonPanel.add(addExpenseButton);
        buttonPanel.add(vspacer1);
        addExpenseButton.setActionCommand("Add expense");
        addExpenseButton.addActionListener(this);
    }

    private void createAddIncomeButton(JPanel vspacer2) {
        //---- addIncomeButton ----
        JButton addIncomeButton = new JButton("Add income");
        buttonPanel.add(addIncomeButton);
        buttonPanel.add(vspacer2);
        addIncomeButton.setActionCommand("Add income");
        addIncomeButton.addActionListener(this);
    }

    private void createFilterButton(JPanel vspacer3) {
        //---- filterButton ----
        JButton filterButton = new JButton("Filter");
        buttonPanel.add(filterButton);
        buttonPanel.add(vspacer3);
        filterButton.setActionCommand("Filter");
        filterButton.addActionListener(this);
    }

    private void createSaveExitButton() {
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
                new AddIncomeWindow(this);
                break;
            case "Filter":
                filterTransactions();
                break;
            case "Save/Exit":
                new SaveExitWindow(this);
                break;
        }
    }

    private void filterTransactions() {
    }

    private void createDetailPanel() {
        detailPanel = new JPanel();
        detailPanel.setBorder(BorderFactory.createBevelBorder(1));

        detailPanel.setBorder(new javax.swing.border.CompoundBorder(BorderFactory.createBevelBorder(1),
                new javax.swing.border.TitledBorder(detailPanel.getBorder(),
                        "Budget Details:", javax.swing.border.TitledBorder.LEFT,
                        javax.swing.border.TitledBorder.TOP, new Font("Arial", Font.BOLD, 14),
                        Color.blue)));

        detailPanel.setLayout(new GridLayout(1, 3));

        addTotalsPanel();
        addExpensesChartPanel();
        addIncomeChartPanel();
    }

    private void addTotalsPanel() {
        //======== totals panel ========
        JPanel totalsPanel = new JPanel();
        totalsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        totalsPanel.setLayout(new GridLayout(3, 0));

        //---- balance ----
        JLabel balance = new JLabel("Budget balance: ");
        balance.setHorizontalAlignment(SwingConstants.CENTER);
        totalsPanel.add(balance);

        //---- expenseTotal
        JLabel expenseTotal = new JLabel("Total expenses: ");
        expenseTotal.setHorizontalAlignment(SwingConstants.CENTER);
        totalsPanel.add(expenseTotal);

        //---- incomeTotal ----
        JLabel incomeTotal = new JLabel("Total income:");
        incomeTotal.setHorizontalAlignment(SwingConstants.CENTER);
        totalsPanel.add(incomeTotal);

        detailPanel.add(totalsPanel);
    }

    private void addExpensesChartPanel() {
        // income chart panel
        JPanel incomePanel = new JPanel();
        incomePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        incomePanel.setLayout(new FlowLayout());
        detailPanel.add(incomePanel);
    }

    private void addIncomeChartPanel() {
        // expenses chart panel
        JPanel expensesPanel = new JPanel();
        expensesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        expensesPanel.setLayout(new FlowLayout());
        detailPanel.add(expensesPanel);

        pane.add(detailPanel);
    }


    private void createBudgetPanel() {
        budgetPanel = new JPanel();
        budgetPanel.setBorder(BorderFactory.createBevelBorder(1));
        budgetPanel.setLayout(new GridLayout());

        budgetPanel.setBorder(new javax.swing.border.CompoundBorder(BorderFactory.createBevelBorder(1),
                new javax.swing.border.TitledBorder(budgetPanel.getBorder(), "Transactions:",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new Font("Arial", Font.BOLD, 14), Color.blue)));

        createBudgetTable();
    }

    private void createBudgetTable() {
        tableModel = new DefaultTableModel();
        budgetTable = new JTable(tableModel);

        addTableColumns();
        addTransactionDetails();

        budgetTable.setColumnSelectionAllowed(true);
        budgetTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(budgetTable);

        budgetPanel.add(scrollPane);
        pane.add(budgetPanel);
    }

    private void addTableColumns() {
        columnNames = new String[]{"Type", "Category", "Description", "Amount"};

        for (int i = 0; i < 4; i++) {
            tableModel.addColumn(columnNames[i]);
        }
    }

    private void addTransactionDetails() {
        transactionArray = getArrayOfCostDetails();

        for (int i = 0; i < transactionArray.length; i++) {
            tableModel.addRow(transactionArray[i]);
        }
    }


    private Object[][] getArrayOfCostDetails() {
        ArrayList<Transaction> transactions = user.getAllTransactions().getTransactions();

        transactionArray = new Object[transactions.size()][];

        int i = 0;
        String type;

        for (Transaction t : transactions) {

            if (costCategories.contains(t.getCategory())) {
                type = "Expenses";
            } else {
                type = "Income";
            }

            String category = Category.getCatString(t.getCategory());
            String description = t.getDescription();
            double amount = t.getAmount();

            Object[] transaction = {type, category, description, amount};
            transactionArray[i] = transaction;
            i++;
        }

        return transactionArray;
    }

    public void updateTable() {
        addMostRecentTransactionToTable();
        budgetTable.updateUI();
        budgetPanel.updateUI();
    }

    private void addMostRecentTransactionToTable() {
        transactionArray = getArrayOfCostDetails();

        int i = transactionArray.length - 1;
        tableModel.addRow(transactionArray[i]);
    }
}
