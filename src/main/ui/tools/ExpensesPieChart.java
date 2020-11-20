package ui.tools;

import model.Category;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import ui.BudgetAppGUI;

// Represents window that constructs and displays expenses pie chart
public class ExpensesPieChart extends PieChartWindow {
    private String title;

    // Constructs new expenses pie chart
    public ExpensesPieChart(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI);
        title = "Expenses";
        setContentPane(createChartPanel(title));
    }

    // EFFECTS: creates dataSet of expenses to be displayed in chart
    @Override
    PieDataset createDataSet() {
        DefaultPieDataset dataSet = new DefaultPieDataset();

        double totalBills = getTotalCostsInCategory(Category.BILLS);
        dataSet.setValue("Bills", totalBills);

        double totalDebtRepayments = getTotalCostsInCategory(Category.DEBT_REPAYMENTS);
        dataSet.setValue("Debt repayments", totalDebtRepayments);

        double totalOneTimeExpenses =  getTotalCostsInCategory(Category.ONE_TIME_EXPENSES);
        dataSet.setValue("One-time expenses", totalOneTimeExpenses);

        double totalMiscPurchases = getTotalCostsInCategory(Category.MISCELLANEOUS_PURCHASES);
        dataSet.setValue("Miscellaneous purchases", totalMiscPurchases);

        double totalForFun = getTotalCostsInCategory(Category.FOR_FUN);
        dataSet.setValue("For fun", totalForFun);

        return dataSet;
    }

}
