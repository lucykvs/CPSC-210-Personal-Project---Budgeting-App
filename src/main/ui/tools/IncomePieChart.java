package ui.tools;

import model.Category;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import ui.BudgetAppGUI;

// Represents window that constructs and displays income pie chart
public class IncomePieChart extends PieChartWindow {
    private String title;

    // EFFECTS: constructs new income pie chart
    public IncomePieChart(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI);
        title = "Income";
        setContentPane(createChartPanel(title));
    }

    // EFFECTS: creates dataSet of income to be displayed in chart
    @Override
    PieDataset createDataSet() {
        DefaultPieDataset dataSet = new DefaultPieDataset();

        double totalEmployment = getTotalCostsInCategory(Category.EMPLOYMENT);
        dataSet.setValue("Employment", totalEmployment);

        double totalLoans = getTotalCostsInCategory(Category.LOAN);
        dataSet.setValue("Loans", totalLoans);

        double totalGift =  getTotalCostsInCategory(Category.GIFT);
        dataSet.setValue("Gifts", totalGift);

        double totalOther = getTotalCostsInCategory(Category.OTHER);
        dataSet.setValue("Other", totalOther);

        return dataSet;
    }

}
