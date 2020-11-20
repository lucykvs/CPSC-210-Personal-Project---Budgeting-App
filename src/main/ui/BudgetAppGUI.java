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

    public User getUser() {
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

    // MODIFIES:
    private void createButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createBevelBorder(1));

        buttonPanel.setLayout(new GridLayout(9, 0));
        JPanel vspacer1 = new JPanel(null);
        JPanel vspacer2 = new JPanel(null);
        JPanel vspacer3 = new JPanel(null);
        JPanel vspacer4 = new JPanel(null);

        createAddExpenseButton(vspacer1);
        createRemoveExpenseButton(vspacer2);
        createAddIncomeButton(vspacer3);
        createRemoveIncomeButton(vspacer4);
        //createFilterButton(vspacer3);
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

    private void createRemoveExpenseButton(JPanel vspacer2) {
        //---- removeExpenseButton ----
        JButton removeExpenseButton = new JButton("Remove expense");
        buttonPanel.add(removeExpenseButton);
        buttonPanel.add(vspacer2);
        removeExpenseButton.setActionCommand("Remove expense");
        removeExpenseButton.addActionListener(this);
    }

    private void createAddIncomeButton(JPanel vspacer3) {
        //---- addIncomeButton ----
        JButton addIncomeButton = new JButton("Add income");
        buttonPanel.add(addIncomeButton);
        buttonPanel.add(vspacer3);
        addIncomeButton.setActionCommand("Add income");
        addIncomeButton.addActionListener(this);
    }

    private void createRemoveIncomeButton(JPanel vspacer4) {
        //---- removeIncomeButton ----
        JButton removeIncomeButton = new JButton("Remove income");
        buttonPanel.add(removeIncomeButton);
        buttonPanel.add(vspacer4);
        removeIncomeButton.setActionCommand("Remove income");
        removeIncomeButton.addActionListener(this);
    }

//    private void createFilterButton(JPanel vspacer3) {
//        //---- filterButton ----
//        JButton filterButton = new JButton("Filter");
//        buttonPanel.add(filterButton);
//        buttonPanel.add(vspacer3);
//        filterButton.setActionCommand("Filter");
//        filterButton.addActionListener(this);
//    }

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
            case "Remove expense":
                new RemoveExpenseWindow(this);
                break;
            case "Add income":
                new AddIncomeWindow(this);
                break;
            case "Remove income":
                new RemoveIncomeWindow(this);
                break;
//            case "Filter":
//                filterTransactions();
//                break;
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
        totalsPanel.setLayout(new GridLayout(6, 0));

        createBalanceCalculation(totalsPanel);
        createExpenseCalculation(totalsPanel);
        createIncomeCalculation(totalsPanel);
        detailPanel.add(totalsPanel);
    }

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

    private void addExpensesChartPanel() {
        // expenses chart panel
        JPanel expensesPanel = new JPanel();
        expensesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        expensesPanel.setLayout(new FlowLayout());
        detailPanel.add(expensesPanel);

        pane.add(detailPanel);

        JButton expensesChartButton = new JButton("Generate expense pie chart");
        expensesChartButton.setActionCommand("expenses chart");
        setUpExpensesChartButton(expensesChartButton, this);

        expensesPanel.add(expensesChartButton);
    }

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

    private void addIncomeChartPanel() {
        // income chart panel
        JPanel incomePanel = new JPanel();
        incomePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        incomePanel.setLayout(new FlowLayout());
        detailPanel.add(incomePanel);

        //createIncomeChart(incomePanel);
        JButton incomeChartButton = new JButton("Generate income pie chart");
        incomeChartButton.setActionCommand("income chart");
        setUpIncomeChartButton(incomeChartButton, this);

        incomePanel.add(incomeChartButton);
    }

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
        transactionArray = getArrayOfTransactionDetails();

        for (Object[] transaction : transactionArray) {
            tableModel.addRow(transaction);
        }
    }


    private Object[][] getArrayOfTransactionDetails() {
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

    public void updateTableWithAddedTransaction() {
        addMostRecentTransactionToTable();
        budgetTable.updateUI();
        budgetPanel.updateUI();
    }

    private void addMostRecentTransactionToTable() {
        transactionArray = getArrayOfTransactionDetails();

        int i = transactionArray.length - 1;
        tableModel.addRow(transactionArray[i]);
    }

    public boolean updateTableWithRemovedTransaction(Transaction t) {
        if (removeSpecifiedTransactionFromTable(t)) {
            budgetTable.updateUI();
            budgetPanel.updateUI();
            return true;
        } else {
            return false;
        }
    }


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

    private int getIndex(ArrayList<Transaction> transactions, Transaction t) {
        return transactions.indexOf(t);
    }
}
