package ui.tools;

import model.Category;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import ui.BudgetAppGUI;

public class IncomePieChart extends PieChartWindow {
    private String title;

    public IncomePieChart(BudgetAppGUI budgetAppGUI) {
        super(budgetAppGUI);
        title = "Income";
        setContentPane(createChartPanel(title));
    }

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
