package ui;

import model.Category;
import model.Transaction;
import model.User;
import org.jfree.ui.RefineryUtilities;
import ui.tools.*;

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

public class BudgetAppGUI extends JFrame implements ActionListener {
    private JPanel userPanel;
    private JPanel detailPanel;
    private JPanel buttonPanel;
    private JPanel budgetPanel;
    private JTable budgetTable;
    private DefaultTableModel tableModel;
    private static User user;
    private Object[][] transactionArray;
    private Container pane;
    private JComboBox<String> filterBox;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    // EFFECTS: runs GUI of budget application
    public static void main(String[] args) {
        new StartUpWindow().setVisible(true);
    }

    // EFFECTS: runs the main budget application frame
    public BudgetAppGUI() {
        super("Budget Application");
        initializeGraphics();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window where this BudgetApp will operate, and adds components that make up app
    private void initializeGraphics() {
        pane = getContentPane();

        addComponentsToPane();

        pack();
    }

    // MODIFIES: this
    // EFFECTS: sets this user to new user with given username
    public static void setUser(String username) {
        user = new User(username);
    }

    // MODIFIES: this
    // EFFECTS: sets this user to given user
    public static void setUser(User previousUser) {
        user = previousUser;
    }

    // EFFECTS: returns this user
    public User getUser() {
        return user;
    }

    // MODIFIES: this
    // EFFECTS: creates panels that make up app frame
    public void addComponentsToPane() {
        createUserPanel();
        createDetailPanel();
        createBudgetPanel();
        createButtonPanel();
        setContentPaneLayout();
    }

    // MODIFIES: this
    // EFFECTS: sets layout of main application frame
    private void setContentPaneLayout() {
        GroupLayout contentPaneLayout = new GroupLayout(pane);

        pane.setLayout(contentPaneLayout);
        setHorizontalGroup(contentPaneLayout);
        setVerticalGroup(contentPaneLayout);

        pack();
        setLocationRelativeTo(getOwner());
    }

    // MODIFIES: this
    // EFFECTS: sets vertical group layout of panels on frame in correct orientation
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

    // MODIFIES: this
    // EFFECTS: sets horizontal group layout of panels on frame in correct orientation
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

    // MODIFIES: this
    // EFFECTS: creates panel with username and app photo
    private void createUserPanel() {
        userPanel = new JPanel();
        userPanel.setMinimumSize(new Dimension(80, HEIGHT / 4));
        userPanel.setMaximumSize(new Dimension(WIDTH / 4, HEIGHT / 4));
        userPanel.setPreferredSize(new Dimension(WIDTH / 4, HEIGHT / 4));
        userPanel.setBorder(BorderFactory.createBevelBorder(1));
        userPanel.setBorder(new javax.swing.border.CompoundBorder(BorderFactory.createBevelBorder(1),
                new javax.swing.border.TitledBorder(userPanel.getBorder(),"Username: " + getUser().getName(),
                        javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.TOP,
                        new Font("Arial",Font.BOLD,14), Color.blue)));
        userPanel.setOpaque(true);
        userPanel.setBackground(Color.WHITE);
        addUserPhoto();
        pane.add(userPanel);
    }

    // MODIFIES: userPanel
    // EFFECTS: loads app photo from file and adds to userPanel
    private void addUserPhoto() {
        try {
            BufferedImage fullSizeUserPic = ImageIO.read(new File("./data/budgetImage2.png"));
            Image scaledUserPic = fullSizeUserPic.getScaledInstance(userPanel.getPreferredSize().width - 40,
                    userPanel.getPreferredSize().height, Image.SCALE_SMOOTH);
            JLabel imagePanel = new JLabel(new ImageIcon(scaledUserPic));
            userPanel.add(imagePanel);
            imagePanel.setHorizontalAlignment(SwingConstants.CENTER);
            imagePanel.setVerticalAlignment(SwingConstants.BOTTOM);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: buttonPanel
    // EFFECTS: sets up border, layout, and components of buttonPanel
    private void createButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createBevelBorder(1));

        buttonPanel.setLayout(new GridLayout(9, 0));
        JPanel vspacer1 = new JPanel(null);
        vspacer1.setBackground(Color.WHITE);
        vspacer1.setOpaque(true);
        JPanel vspacer2 = new JPanel(null);
        vspacer2.setBackground(Color.WHITE);
        vspacer2.setOpaque(true);
        JPanel vspacer3 = new JPanel(null);
        vspacer3.setBackground(Color.WHITE);
        vspacer3.setOpaque(true);
        JPanel vspacer4 = new JPanel(null);
        vspacer4.setBackground(Color.WHITE);
        vspacer4.setOpaque(true);

        createAddExpenseButton(vspacer1);
        createRemoveExpenseButton(vspacer2);
        createAddIncomeButton(vspacer3);
        createRemoveIncomeButton(vspacer4);
        createSaveExitButton();
    }

    // MODIFIES: buttonPanel
    // EFFECTS: creates button for adding expenses
    private void createAddExpenseButton(JPanel vspacer1) {
        JButton addExpenseButton = new JButton("Add expense");
        buttonPanel.add(addExpenseButton);
        buttonPanel.add(vspacer1);
        addExpenseButton.setActionCommand("Add expense");
        addExpenseButton.addActionListener(this);
    }

    // MODIFIES: buttonPanel
    // EFFECTS: creates button for removing expenses
    private void createRemoveExpenseButton(JPanel vspacer2) {
        JButton removeExpenseButton = new JButton("Remove expense");
        buttonPanel.add(removeExpenseButton);
        buttonPanel.add(vspacer2);
        removeExpenseButton.setActionCommand("Remove expense");
        removeExpenseButton.addActionListener(this);
    }

    // MODIFIES: buttonPanel
    // EFFECTS: creates button for adding income
    private void createAddIncomeButton(JPanel vspacer3) {
        JButton addIncomeButton = new JButton("Add income");
        buttonPanel.add(addIncomeButton);
        buttonPanel.add(vspacer3);
        addIncomeButton.setActionCommand("Add income");
        addIncomeButton.addActionListener(this);
    }

    // MODIFIES: buttonPanel
    // EFFECTS: creates button for removing income
    private void createRemoveIncomeButton(JPanel vspacer4) {
        JButton removeIncomeButton = new JButton("Remove income");
        buttonPanel.add(removeIncomeButton);
        buttonPanel.add(vspacer4);
        removeIncomeButton.setActionCommand("Remove income");
        removeIncomeButton.addActionListener(this);
    }

    // MODIFIES: buttonPanel
    // EFFECTS: creates button to open SaveExitWindow
    private void createSaveExitButton() {
        JButton saveExitButton = new JButton("Save/Exit");
        buttonPanel.add(saveExitButton);
        saveExitButton.setActionCommand("Save/Exit");
        saveExitButton.addActionListener(this);

        pane.add(buttonPanel);
    }

    // MODIFIES: this
    // EFFECTS: implements action events for buttons on button panel
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add expense":
                // opens window for expense details to be added
                new AddExpenseWindow(this);
                break;
            case "Remove expense":
                // opens window for expense details to be removed
                new RemoveExpenseWindow(this);
                break;
            case "Add income":
                // opens window for income details to be added
                new AddIncomeWindow(this);
                break;
            case "Remove income":
                // opens window for income details to be removed
                new RemoveIncomeWindow(this);
                break;
            case "Save/Exit":
                // opens window for save/exit options
                new SaveExitWindow(this);
                break;
        }
    }

    // MODIFIES: this, detailPanel
    // EFFECTS: creates upper right panel which houses options to get budget details
    private void createDetailPanel() {
        detailPanel = new JPanel();
        detailPanel.setBorder(BorderFactory.createBevelBorder(1));

        detailPanel.setBorder(new javax.swing.border.CompoundBorder(BorderFactory.createBevelBorder(1),
                new javax.swing.border.TitledBorder(detailPanel.getBorder(),
                        "Budget Details:", javax.swing.border.TitledBorder.LEFT,
                        javax.swing.border.TitledBorder.TOP, new Font("Arial", Font.BOLD, 14),
                        Color.blue)));

        detailPanel.setLayout(new GridLayout(1, 2));

        addTotalsPanel();
        addChartPanel();
    }

    // MODIFIES: detailsPanel
    // EFFECTS: creates panel to calculate balance, expenses, and income totals, added to detailPanel
    private void addTotalsPanel() {
        JPanel totalsPanel = new JPanel();
        totalsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        totalsPanel.setLayout(new GridLayout(6, 0));

        createBalanceCalculation(totalsPanel);
        createExpenseCalculation(totalsPanel);
        createIncomeCalculation(totalsPanel);
        detailPanel.add(totalsPanel);
    }

    // MODIFIES: totalsPanel
    // EFFECTS: creates JButton that will calculate balance
    private void createBalanceCalculation(JPanel totalsPanel) {
        JButton calculateBalance = new JButton("Calculate budget balance: ");
        calculateBalance.setHorizontalAlignment(SwingConstants.CENTER);
        calculateBalance.setBounds(10, 10, 40, 20);

        JLabel balanceLabel = new JLabel();
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceLabel.setOpaque(true);
        balanceLabel.setBackground(Color.WHITE);

        totalsPanel.add(calculateBalance);
        totalsPanel.add(balanceLabel);

        setUpBalanceCalculationButton(calculateBalance, balanceLabel);
    }

    // MODIFIES: calculateBalance JButton, balanceLabel
    // EFFECTS: calculates and displays budget balance when calculateBalance button is clicked
    private void setUpBalanceCalculationButton(JButton calculateBalance, JLabel balanceLabel) {
        calculateBalance.setActionCommand("balance");
        calculateBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("balance")) {
                    double balance = getUser().getBudgetBalance();
                    balanceLabel.setText(String.valueOf(balance));

                    if (balance >= 0) {
                        balanceLabel.setForeground(Color.GREEN);
                    } else {
                        balanceLabel.setForeground(Color.RED);
                    }
                }
            }
        });
    }

    // MODIFIES: totalsPanel
    // EFFECTS: creates JButton, JLabel that will calculate and display total expenses
    private void createExpenseCalculation(JPanel totalsPanel) {
        JButton calculateExpenses = new JButton("Calculate total expenses: ");
        calculateExpenses.setHorizontalAlignment(SwingConstants.CENTER);
        calculateExpenses.setBounds(10, 10, 40, 20);

        JLabel totalExpenses = new JLabel();
        totalExpenses.setHorizontalAlignment(SwingConstants.CENTER);
        totalExpenses.setOpaque(true);
        totalExpenses.setBackground(Color.WHITE);

        totalsPanel.add(calculateExpenses);
        totalsPanel.add(totalExpenses);

        calculateExpenses.setActionCommand("expenses");
        calculateExpenses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("expenses")) {
                    totalExpenses.setText(String.valueOf(getUser().getTotalExpenseAmount()));
                }
            }
        });
    }

    // MODIFIES: totalsPanel
    // EFFECTS: creates JButton, JLabel that will calculate and display total income
    private void createIncomeCalculation(JPanel totalsPanel) {
        JButton calculateIncome = new JButton("Calculate total income: ");
        calculateIncome.setHorizontalAlignment(SwingConstants.CENTER);
        calculateIncome.setBounds(10, 10, 40, 20);

        JLabel totalIncome = new JLabel();
        totalIncome.setHorizontalAlignment(SwingConstants.CENTER);
        totalIncome.setOpaque(true);
        totalIncome.setBackground(Color.WHITE);

        totalsPanel.add(calculateIncome);
        totalsPanel.add(totalIncome);

        calculateIncome.setActionCommand("income");
        calculateIncome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("income")) {
                    totalIncome.setText(String.valueOf(getUser().getTotalIncomeAmount()));
                }
            }
        });
    }

    // MODIFIES: totalsPanel
    // EFFECTS: creates panel that houses options for getting expense details; buttons for opening
    //          expensePieChart, incomePieChart, and filtering table
    private void addChartPanel() {
        JPanel chartPanel = new JPanel();
        chartPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        chartPanel.setLayout(new GridLayout(6, 0));
        detailPanel.add(chartPanel);
        pane.add(detailPanel);
        chartPanel.setOpaque(true);

        chartPanel.setBackground(Color.white);
        JButton expensesChartButton = new JButton("Generate expense pie chart");
        expensesChartButton.setActionCommand("expenses chart");
        setUpExpensesChartButton(expensesChartButton, this);

        JButton incomeChartButton = new JButton("Generate income pie chart");
        incomeChartButton.setActionCommand("income chart");
        setUpIncomeChartButton(incomeChartButton, this);

        addButtonsToDetailPanel(chartPanel, expensesChartButton, incomeChartButton);
    }

    // MODIFIES: detailPanel
    // EFFECTS: adds buttons to detailPanel, with vertical spacer panels
    private void addButtonsToDetailPanel(JPanel chartPanel, JButton expensesChartButton, JButton incomeChartButton) {
        JPanel vspace1 = new JPanel(null);
        JPanel vspace2 = new JPanel(null);
        vspace1.setOpaque(true);
        vspace1.setBackground(Color.white);
        vspace2.setOpaque(true);
        vspace2.setBackground(Color.white);

        chartPanel.add(expensesChartButton);
        chartPanel.add(vspace1);
        chartPanel.add(incomeChartButton);
        createFilterBox(chartPanel);
    }

    // EFFECTS: when expensesChartButton is clicked, opens new ExpensePieChart window
    private void setUpExpensesChartButton(JButton expensesChartButton, BudgetAppGUI budgetAppGUI) {
        expensesChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("expenses chart")) {
                    ExpensesPieChart expensesPieChart = new ExpensesPieChart(budgetAppGUI);
                    expensesPieChart.setSize(560, 367);
                    RefineryUtilities.centerFrameOnScreen(expensesPieChart);
                    expensesPieChart.setVisible(true);
                }
            }
        });
    }

    // EFFECTS: when incomeChartButton is clicked, opens new IncomePieChart window
    private void setUpIncomeChartButton(JButton incomeChartButton, BudgetAppGUI budgetAppGUI) {
        incomeChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("income chart")) {
                    IncomePieChart incomePieChart = new IncomePieChart(budgetAppGUI);
                    incomePieChart.setSize(560, 367);
                    RefineryUtilities.centerFrameOnScreen(incomePieChart);
                    incomePieChart.setVisible(true);
                }
            }
        });
    }

    // MODIFIES: detailPanel
    // EFFECTS: creates filter button to show only expenses or only income in budgetTable
    private void createFilterBox(JPanel chartPanel) {
        JLabel filterLabel = new JLabel("Filter table by:");
        String[] filterOptions = {"All transactions", "Expenses only", "Income only"};
        filterBox = new JComboBox<>(filterOptions);
        JPanel vspace = new JPanel(null);
        vspace.setOpaque(true);
        vspace.setBackground(Color.white);

        chartPanel.add(filterLabel);
        chartPanel.add(filterBox);
        chartPanel.add(vspace);

        filterBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) filterBox.getSelectedItem();
                filterTable(s);
            }
        });
    }

    // MODIFIES: budgetPanel
    // EFFECTS: filters table based on selection from filterBox
    private void filterTable(String s) {
        if (s.equals("All transactions")) {
            ArrayList<Transaction> transactions = user.getAllTransactions().getTransactions();
            transactionArray = new Object[transactions.size()][];
            createBudgetTable(transactions, transactionArray);
        } else if (s.equals("Expenses only")) {
            ArrayList<Transaction> transactions = user.getExpenses().getTransactions();
            transactionArray = new Object[transactions.size()][];
            createBudgetTable(transactions, transactionArray);
        } else {
            ArrayList<Transaction> transactions = user.getIncome().getTransactions();
            transactionArray = new Object[transactions.size()][];
            createBudgetTable(transactions, transactionArray);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates lower left panel to display budget in table format
    private void createBudgetPanel() {
        budgetPanel = new JPanel();
        budgetPanel.setBorder(BorderFactory.createBevelBorder(1));
        budgetPanel.setLayout(new GridLayout());

        budgetPanel.setBorder(new javax.swing.border.CompoundBorder(BorderFactory.createBevelBorder(1),
                new javax.swing.border.TitledBorder(budgetPanel.getBorder(), "Transactions:",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new Font("Arial", Font.BOLD, 14), Color.blue)));

        ArrayList<Transaction> transactions = user.getAllTransactions().getTransactions();
        transactionArray = new Object[transactions.size()][];
        createBudgetTable(transactions, transactionArray);
    }

    // MODIFIES: this, budgetPanel
    // EFFECTS: creates budgetTable and adds it to budgetPanel
    private void createBudgetTable(ArrayList<Transaction> transactions, Object[][] transactionArray) {
        budgetPanel.removeAll();
        tableModel = new DefaultTableModel();
        budgetTable = new JTable(tableModel);

        addTableColumns();
        addTransactionDetails(transactions, transactionArray);

        budgetTable.setColumnSelectionAllowed(true);
        budgetTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(budgetTable);

        budgetPanel.add(scrollPane);
        pane.add(budgetPanel);

        budgetTable.updateUI();
        budgetPanel.updateUI();
    }

    // EFFECTS: creates String array of column names for budgetTable
    private void addTableColumns() {
        String[] columnNames = new String[]{"Type", "Category", "Description", "Amount"};

        for (int i = 0; i < 4; i++) {
            tableModel.addColumn(columnNames[i]);
        }
    }

    // MODIFIES: budgetTable
    // EFFECTS: gets array of all transaction details and adds each transaction to budgetTable
    private void addTransactionDetails(ArrayList<Transaction> transactions, Object[][] transactionArray) {
        getArrayOfTransactionDetails(transactions, transactionArray);

        for (Object[] transaction : transactionArray) {
            tableModel.addRow(transaction);
        }
    }

    // EFFECTS: gets transaction information from this user and puts it into an array for use in
    //          budgetTable
    private Object[][] getArrayOfTransactionDetails(ArrayList<Transaction> transactions, Object[][] transactionArray) {
        int i = 0;
        String type;

        for (Transaction t : transactions) {

            if (t.getType().equals("expense")) {
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

    // MODIFIES: budgetTable
    // EFFECTS: updates budgetTable when a new expense or income is added
    public void updateTableWithAddedTransaction() {
        addMostRecentTransactionToTable();
        budgetTable.updateUI();
        budgetPanel.updateUI();
    }

    // MODIFIES: budgetTable
    // EFFECTS: gets most recent transaction added to array of transaction details and adds it
    //          to budgetTable
    private void addMostRecentTransactionToTable() {
        ArrayList<Transaction> transactions = user.getAllTransactions().getTransactions();
        transactionArray = new Object[transactions.size()][];

        transactionArray = getArrayOfTransactionDetails(transactions, transactionArray);

        int i = transactionArray.length - 1;
        tableModel.addRow(transactionArray[i]);
    }

    // MODIFIES: budgetTable
    // EFFECTS: updates budgetTable when an income or expense is removed; returns true if a
    //          transaction was removed, false otherwise
    public boolean updateTableWithRemovedTransaction(Transaction t) {
        if (removeSpecifiedTransactionFromTable(t)) {
            budgetTable.updateUI();
            budgetPanel.updateUI();
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: budgetTable
    // EFFECTS: searchs transactions for specified income or expense to be removed; if found,
    //          transaction is removed and returns true; if not found, returns false
    private boolean removeSpecifiedTransactionFromTable(Transaction transaction) {
        ArrayList<Transaction> transactions = getUser().getAllTransactions().getTransactions();

        for (Transaction t : transactions) {
            if (t.equals(transaction)) {
                int index = getIndex(transactions, t);
                tableModel.removeRow(index);
                return getUser().removeTransaction(t);
            }
        }
        return false;
    }

    // EFFECTS: returns index of matching transaction found in user's transactions
    private int getIndex(ArrayList<Transaction> transactions, Transaction t) {
        return transactions.indexOf(t);
    }
}
